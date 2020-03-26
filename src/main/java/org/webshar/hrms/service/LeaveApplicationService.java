package org.webshar.hrms.service;

import static org.webshar.hrms.enums.LeaveStatus.CANCELED;
import static org.webshar.hrms.enums.LeaveStatus.PENDING;
import static org.webshar.hrms.enums.LeaveStatus.REJECTED;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
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
import org.webshar.hrms.request.employee.leave.application.EmployeeLeaveApplicationCreateRequest;
import org.webshar.hrms.request.employee.leave.application.EmployeeLeaveApplicationUpdateRequest;
import org.webshar.hrms.response.builder.LeaveApplicationResponseBuilder;
import org.webshar.hrms.response.employee.leave.application.EmployeeLeaveApplicationResponse;
import org.webshar.hrms.service.exception.EntityAlreadyExistsException;
import org.webshar.hrms.service.exception.EntityNotFoundException;
import org.webshar.hrms.service.exception.InsufficientLeaveException;

@Service
@Transactional
public class LeaveApplicationService
{

  public static final String DATE_FORMAT = "yyyy-MM-dd";

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

  @Autowired
  LeaveApplicationResponseBuilder leaveApplicationResponseBuilder;

  public List<EmployeeLeaveApplicationResponse> getAllAppliedLeavesOfAllEmployees()
  {
    return leaveApplicationResponseBuilder.buildFromResult( leaveApplicationRepository.findAll());
  }

  public EmployeeLeaveApplicationResponse getLeaveById(final Long id) throws EntityNotFoundException
  {
    LeaveApplication leaveApplication = leaveApplicationRepository.findById(id)
        .orElseThrow(
            () -> new EntityNotFoundException(ErrorMessageConstants.LEAVE_BY_ID_NOT_FOUND));
    return  leaveApplicationResponseBuilder.buildFromResult(leaveApplication);
  }

  public List<EmployeeLeaveApplicationResponse> getLeaveApplicationByEmployeeIdLeaveTypeAndLeaveStatus(
      final Long employeeId, final Long leaveTypeId, final Long leaveStatusId)
      throws EntityNotFoundException
  {
    Employee employee = employeeService.getEmployeeByEmployeeId(employeeId);

    if (employeeId != null && leaveTypeId == null && leaveStatusId == null)
    {
      return leaveApplicationResponseBuilder.buildFromResult(leaveApplicationRepository.findByEmployeeId(employee.getId()));
    }
    else if (employeeId != null && leaveTypeId != null && leaveStatusId == null)
    {
      return leaveApplicationResponseBuilder.buildFromResult(leaveApplicationRepository
          .findByEmployeeIdAndLeaveTypeId(employee.getId(), leaveTypeId));
    }
    else if (employeeId != null && leaveTypeId != null && leaveStatusId != null)
    {
      return leaveApplicationResponseBuilder.buildFromResult(leaveApplicationRepository
          .findByEmployeeIdAndLeaveTypeIdAndLeaveStatusId(employee.getId(), leaveTypeId,
              leaveStatusId));
    }
    return leaveApplicationResponseBuilder.buildFromResult(new ArrayList<LeaveApplication>());
  }

  public EmployeeLeaveApplicationResponse applyLeave(
      final EmployeeLeaveApplicationCreateRequest employeeLeaveApplicationCreateRequest)
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

    Collection<Long> leaveStatusIds = new ArrayList<>();
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
      isStartAndEndDateAreOverlappingWithExistingRecordForGivenEmployeeAndLeaveTypeId(
          employee.getId(), leaveType.getId(), employeeLeaveApplicationCreateRequest.getStartDate(),
          employeeLeaveApplicationCreateRequest.getEndDate());
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
      return leaveApplicationResponseBuilder.buildFromResult(leaveApplicationRepository.save(leaveToApply));
    }
  }


  public EmployeeLeaveApplicationResponse updateLeaveApplication(
      final Long id, final EmployeeLeaveApplicationUpdateRequest employeeLeaveApplicationUpdateRequest)
      throws EntityNotFoundException {

    Employee employee = employeeService
        .getEmployeeByEmployeeId(employeeLeaveApplicationUpdateRequest.getEmployeeId());
    LeaveStatus leaveStatus = leaveStatusService
        .getLeaveStatusById(employeeLeaveApplicationUpdateRequest.getLeaveStatusId());

    LeaveApplication leaveApplicationToUpdate = leaveApplicationRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(ErrorMessageConstants.LEAVE_BY_ID_NOT_FOUND));

    LeaveApplication updatedLeaveApplication = leaveApplicationBuilder
        .buildFromRequest(leaveApplicationToUpdate, employee, leaveStatus);

    updatedLeaveApplication = leaveApplicationRepository.save(updatedLeaveApplication);

    return leaveApplicationResponseBuilder.buildFromResult(updatedLeaveApplication);
  }

  private int getNoOfDaysLeaveApplied(final Date startDate, final Date endDate)
  {
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
    LocalDate ldStartDate = LocalDate
        .parse(new SimpleDateFormat(DATE_FORMAT).format(startDate), dateTimeFormatter);
    LocalDate ldEndDate = LocalDate
        .parse(new SimpleDateFormat(DATE_FORMAT).format(endDate), dateTimeFormatter);
    return (int) ChronoUnit.DAYS.between(ldStartDate, ldEndDate) + 1;
  }

  private void isStartAndEndDateAreOverlappingWithExistingRecordForGivenEmployeeAndLeaveTypeId(
      final Long employeeId, final Long leaveTypeId, final Date startDate, final Date endDate)
      throws EntityAlreadyExistsException
  {
    List<LeaveApplication> leaveApplicationList = leaveApplicationRepository.findAllByStartDateGreaterThanEqualAndStartDateLessThanEqual(startDate,endDate);

    leaveApplicationList.addAll(leaveApplicationRepository.findAllByEndDateGreaterThanEqualAndEndDateLessThanEqual(startDate,endDate));
    leaveApplicationList.addAll(leaveApplicationRepository.findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(startDate,endDate));

    Optional<LeaveApplication>  leaveApplicationResult = leaveApplicationList
        .stream()
        .filter(leaveApplication -> (leaveApplication.getEmployee().getId() == employeeId) && (leaveApplication.getLeaveType().getId() == leaveTypeId))
        .findFirst();

    if (leaveApplicationResult.isPresent())
    {
      throw new EntityAlreadyExistsException(
          ErrorMessageConstants.LEAVE_ALLOCATED_WITH_GIVEN_LEAVE_TYPE_AND_START_DATE_AND_END_DATE_OVERLAPPING);
    }
  }
}
