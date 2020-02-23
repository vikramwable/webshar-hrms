package org.webshar.hrms.service;

import static org.webshar.hrms.enums.LeaveStatus.CANCELED;
import static org.webshar.hrms.enums.LeaveStatus.PENDING;
import static org.webshar.hrms.enums.LeaveStatus.REJECTED;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webshar.hrms.constants.ErrorMessageConstants;
import org.webshar.hrms.model.builder.LeaveApplicationBuilder;
import org.webshar.hrms.model.db.Employee;
import org.webshar.hrms.model.db.LeaveAllocation;
import org.webshar.hrms.model.db.LeaveApplication;
import org.webshar.hrms.model.db.LeaveStatus;
import org.webshar.hrms.model.db.LeaveType;
import org.webshar.hrms.repository.LeaveApplicationRepository;
import org.webshar.hrms.request.employee.leave.allocation.EmployeeLeaveAllocationUpdateRequest;
import org.webshar.hrms.request.employee.leave.application.EmployeeLeaveApplicationCreateRequest;
import org.webshar.hrms.request.employee.leave.application.EmployeeLeaveApplicationUpdateRequest;
import org.webshar.hrms.service.exception.EntityAlreadyExistsException;
import org.webshar.hrms.service.exception.InsufficientLeaveException;
import org.webshar.hrms.service.exception.EntityNotFoundException;

@Service
@Transactional
public class LeaveApplicationService
{

  public static String DATE_FORMAT = "yyyy-MM-dd";

  @Autowired
  LeaveApplicationRepository leaveApplicationRepository;

  @Autowired
  EmployeeService employeeService;

  @Autowired
  LeaveTypeService leaveTypeService;

  @Autowired
  LeaveStatusService leaveStatusService;

  @Autowired
  LeaveAllocationService leaveAllocationService;

  @Autowired
  LeaveApplicationBuilder leaveApplicationBuilder;


  public List<LeaveApplication> getAllAppliedLeavesOfAllEmployees()
  {
    return leaveApplicationRepository.findAll();
  }

  public LeaveApplication getLeaveById(Long id) throws EntityNotFoundException
  {
    return leaveApplicationRepository.findById(id)
        .orElseThrow(
            () -> new EntityNotFoundException(ErrorMessageConstants.LEAVE_BY_ID_NOT_FOUND));

    //return leaveResponseBuilder.build(leaveApplication);
  }

  public List<LeaveApplication> getLeaveApplicationByEmployeeIdLeaveTypeAndLeaveStatus(
      Long employeeId, Long leaveTypeId, Long leaveStatusId) throws EntityNotFoundException
  {
    Employee employee = employeeService.getEmployeeByEmployeeId(employeeId);

    if (employeeId != null && leaveTypeId == null && leaveStatusId == null)
    {
      return leaveApplicationRepository.findByEmployeeId(employee.getId());
    }
    else if (employeeId != null && leaveTypeId != null && leaveStatusId == null)
    {
      return leaveApplicationRepository
          .findByEmployeeIdAndLeaveTypeId(employee.getId(), leaveTypeId);
    }
    else if (employeeId != null && leaveTypeId != null && leaveStatusId != null)
    {
      return leaveApplicationRepository
          .findByEmployeeIdAndLeaveTypeIdAndLeaveStatusId(employee.getId(), leaveTypeId,
              leaveStatusId);
    }
    return new ArrayList<LeaveApplication>();
  }

  public LeaveApplication applyLeave(
      EmployeeLeaveApplicationCreateRequest employeeLeaveApplicationCreateRequest)
      throws EntityNotFoundException, InsufficientLeaveException,
      EntityAlreadyExistsException
  {
    Employee employee = employeeService
        .getEmployeeByEmployeeId(employeeLeaveApplicationCreateRequest.getEmployeeId());

    LeaveType leaveType = leaveTypeService
        .getLeaveTypeById(employeeLeaveApplicationCreateRequest.getLeaveTypeId());

    int noOfDaysLeaveApplied = getNoOfDaysLeaveApplied(
        employeeLeaveApplicationCreateRequest.getStartDate(),
        employeeLeaveApplicationCreateRequest.getEndDate());

    LeaveAllocation allocatedEmployeeLeaves = leaveAllocationService
        .getEmployeeAllocatedLeavesByEmployeeIdAndLeaveType(employee.getId(),
            leaveType.getId());

    Collection<Long> leaveStatusIds = new ArrayList<Long>();
    leaveStatusIds.add(leaveStatusService.getLeaveStatusByStatus(REJECTED.toString()).getId());
    leaveStatusIds.add(leaveStatusService.getLeaveStatusByStatus(CANCELED.toString()).getId());

    List<LeaveApplication> appliedLeaves = leaveApplicationRepository
        .findByEmployeeIdAndLeaveTypeIdAndLeaveStatusIdNotIn(employee.getId(), leaveType
            .getId(), leaveStatusIds);

    int availableLeaves = (int) (allocatedEmployeeLeaves.getTotalLeaves() - appliedLeaves.size());

    if (availableLeaves < noOfDaysLeaveApplied)
    {
      throw new InsufficientLeaveException(
          ErrorMessageConstants.LEAVE_INSUFFICIENT_LEAVE_COUNT_OF_GIVEN_LEAVE_TYPE);
    }
    else
    {
      LeaveApplication leaveToApply = leaveApplicationRepository
          .findByEmployeeIdAndStartDateAndEndDate(
              employee.getId(),
              employeeLeaveApplicationCreateRequest.getStartDate(),
              employeeLeaveApplicationCreateRequest.getEndDate());
      if (leaveToApply == null)
      {
        leaveToApply = leaveApplicationBuilder
            .buildFromRequest(employeeLeaveApplicationCreateRequest, leaveType, employee,
                leaveStatusService.getLeaveStatusByStatus(PENDING.toString()));
      }
      else
      {
        throw new EntityAlreadyExistsException(
            ErrorMessageConstants.LEAVE_WITH_GIVEN_TYPE_AND_STATUS_EXISTS);
      }
      return leaveApplicationRepository.save(leaveToApply);
    }
  }


  public LeaveApplication updateLeaveApplication(
      EmployeeLeaveApplicationUpdateRequest employeeLeaveApplicationUpdateRequest)
      throws EntityNotFoundException
  {

    Employee employee = employeeService
        .getEmployeeByEmployeeId(employeeLeaveApplicationUpdateRequest.getEmployeeId());
    LeaveStatus leaveStatus = leaveStatusService
        .getLeaveStatusById(employeeLeaveApplicationUpdateRequest.getLeaveStatusId());

    Optional<LeaveApplication> leaveApplicationToUpdate = leaveApplicationRepository
        .findById(employeeLeaveApplicationUpdateRequest.getId());

    if (leaveApplicationToUpdate.isPresent())
    {
      LeaveApplication updatedLeaveApplication = leaveApplicationBuilder
          .buildFromRequest(leaveApplicationToUpdate.get(),
              employee, leaveStatus);

      updatedLeaveApplication = leaveApplicationRepository.save(updatedLeaveApplication);

      //leaveResponseBuilder.build(updatedLeaveApplication);
      return updatedLeaveApplication;
    }
    else
    {
      throw new EntityNotFoundException(
          ErrorMessageConstants.LEAVE_BY_ID_NOT_FOUND);
    }
  }

  private int getNoOfDaysLeaveApplied(Date startDate, Date endDate)
  {
    DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    LocalDate ldStartDate = LocalDate
        .parse(new SimpleDateFormat(DATE_FORMAT).format(startDate), DATEFORMATTER);
    LocalDate ldEndDate = LocalDate
        .parse(new SimpleDateFormat(DATE_FORMAT).format(endDate), DATEFORMATTER);
    return (int) ChronoUnit.DAYS.between(ldStartDate, ldEndDate) + 1;
  }

  private EmployeeLeaveAllocationUpdateRequest getEmployeeLeaveAllocationUpdateRequest(
      LeaveApplication leaveApplication,
      LeaveAllocation leaveAllocation, int noOfDaysLeaveApplied)
  {
    EmployeeLeaveAllocationUpdateRequest employeeLeaveAllocationUpdateRequest =
        new EmployeeLeaveAllocationUpdateRequest();
    employeeLeaveAllocationUpdateRequest.setId(leaveAllocation.getId());
    employeeLeaveAllocationUpdateRequest.setEmployeeId(leaveApplication.getEmployee().getId());
    employeeLeaveAllocationUpdateRequest.setLeaveTypeId(leaveApplication.getLeaveType().getId());
    employeeLeaveAllocationUpdateRequest.setStartDate(leaveAllocation.getStartDate());
    employeeLeaveAllocationUpdateRequest.setEndDate(leaveAllocation.getEndDate());
    return employeeLeaveAllocationUpdateRequest;
  }
}
