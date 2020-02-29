package org.webshar.hrms.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webshar.hrms.constants.ErrorMessageConstants;
import org.webshar.hrms.model.builder.LeaveAllocationBuilder;
import org.webshar.hrms.model.db.Employee;
import org.webshar.hrms.model.db.LeaveAllocation;
import org.webshar.hrms.model.db.LeaveType;
import org.webshar.hrms.repository.LeaveAllocationRepository;
import org.webshar.hrms.request.employee.leave.allocation.EmployeeLeaveAllocationCreateRequest;
import org.webshar.hrms.request.employee.leave.allocation.EmployeeLeaveAllocationUpdateRequest;
import org.webshar.hrms.response.builder.LeaveAllocationResponseBuilder;
import org.webshar.hrms.response.employee.leave.allocation.LeaveAllocationResponse;
import org.webshar.hrms.service.exception.EntityAlreadyExistsException;
import org.webshar.hrms.service.exception.EntityNotFoundException;

@Service
@Transactional
public class LeaveAllocationService
{

  @Autowired
  LeaveAllocationRepository leaveAllocationRepository;

  @Autowired
  LeaveAllocationBuilder leaveAllocationBuilder;

  @Autowired
  LeaveAllocationResponseBuilder leaveAllocationResponseBuilder;

  @Autowired
  EmployeeService employeeService;

  @Autowired
  LeaveTypeService leaveTypeService;

  public LeaveAllocationResponse getEmployeeAllocatedLeavesById(final Long id)
      throws EntityNotFoundException
  {
    LeaveAllocation leaveAllocation = leaveAllocationRepository.findById(id)
        .orElseThrow(
            () -> new EntityNotFoundException(
                ErrorMessageConstants.EMPLOYEE_LEAVE_BY_ID_NOT_FOUND));
    return leaveAllocationResponseBuilder
        .buildFromResult(leaveAllocation);
  }

  public List<LeaveAllocationResponse> getEmployeesAllocatedLeavesByEmployeeId(
      final Long employeeId)
      throws EntityNotFoundException
  {
    return leaveAllocationResponseBuilder
        .buildFromResult(leaveAllocationRepository
            .findByEmployeeId(employeeService.getEmployeeByEmployeeId(employeeId).getId()));
  }

  public LeaveAllocation getEmployeeAllocatedLeavesByEmployeeIdAndLeaveType(
      final Long employeeId,
      Long leaveTypeId)
      throws EntityNotFoundException
  {

    LeaveAllocation leaveAllocation = leaveAllocationRepository
        .findByEmployeeIdAndLeaveTypeId(employeeId, leaveTypeId);
    if (leaveAllocation != null)
    {
      return leaveAllocation;
    }
    else
    {
      throw new EntityNotFoundException(
          ErrorMessageConstants.EMPLOYEE_LEAVE_GIVEN_TYPE_OF_LEAVE_NOT_ALLOCATED_FOR_GIVEN_EMPLOYEE);
    }
  }

  public LeaveAllocationResponse assignLeavesToAnEmployee(
      final EmployeeLeaveAllocationCreateRequest employeeLeaveAllocationCreateRequest)
      throws EntityAlreadyExistsException, EntityNotFoundException
  {
    Employee employee = employeeService
        .getEmployeeByEmployeeId(employeeLeaveAllocationCreateRequest.getEmployeeId());
    LeaveType leaveType = leaveTypeService
        .getLeaveTypeById(employeeLeaveAllocationCreateRequest.getLeaveTypeId());

    isStartAndEndDateAreOverlappingWithExistingRecordForGivenEmployeeAndLeaveTypeId(
        employee.getId(), leaveType.getId(), employeeLeaveAllocationCreateRequest.getStartDate(),
        employeeLeaveAllocationCreateRequest.getEndDate());

    LeaveAllocation leaveAllocationToCreate = leaveAllocationBuilder
        .buildFromRequest(employeeLeaveAllocationCreateRequest, employee, leaveType);

    return leaveAllocationResponseBuilder
        .buildFromResult(leaveAllocationRepository.save(
            leaveAllocationToCreate));
  }

  public LeaveAllocationResponse updateEmployeesAllocatedLeavesByEmployeeIdAndLeaveType(
      final EmployeeLeaveAllocationUpdateRequest employeeLeaveAllocationUpdateRequest)
      throws EntityNotFoundException, EntityAlreadyExistsException
  {
    Employee employee = employeeService
        .getEmployeeByEmployeeId(employeeLeaveAllocationUpdateRequest.getEmployeeId());
    LeaveType leaveType = leaveTypeService
        .getLeaveTypeById(employeeLeaveAllocationUpdateRequest.getLeaveTypeId());

    isStartAndEndDateAreOverlappingWithExistingRecordForGivenEmployeeAndLeaveTypeId(
        employee.getId(), leaveType.getId(), employeeLeaveAllocationUpdateRequest.getStartDate(),
        employeeLeaveAllocationUpdateRequest.getEndDate());
    Optional<LeaveAllocation> employeeLeaveAllocationToUpdate =
        leaveAllocationRepository
            .findById(employeeLeaveAllocationUpdateRequest.getId());
    if (employeeLeaveAllocationToUpdate.isPresent())
    {
      LeaveAllocation updatedLeaveAllocation =
          leaveAllocationBuilder.buildFromRequest(employeeLeaveAllocationUpdateRequest,
              employeeLeaveAllocationToUpdate.get(), employee, leaveType);

      return leaveAllocationResponseBuilder
          .buildFromResult(leaveAllocationRepository.save(updatedLeaveAllocation));
    }
    else
    {
      throw new EntityNotFoundException(
          ErrorMessageConstants.EMPLOYEE_LEAVE_ALLOCATED_BY_ID_NOT_FOUND);
    }
  }

  public void deleteAllocatedLeavesOfAnEmployeeByEmployeeIdAndLeaveType(final Long employeeId,
      final Long leaveTypeId)
      throws EntityNotFoundException
  {
    Employee employee = employeeService.getEmployeeByEmployeeId(employeeId);
    if (employeeId != null && leaveTypeId != null)
    {
      leaveAllocationRepository
          .deleteByEmployeeIdAndLeaveTypeId(
              employeeService.getEmployeeByEmployeeId(employeeId).getId(),
              leaveTypeService.getLeaveTypeById(leaveTypeId).getId());
    }
    else if (employeeId != null && leaveTypeId == null)
    {
      leaveAllocationRepository
          .deleteByEmployeeId(employeeService.getEmployeeByEmployeeId(employeeId).getId());
    }
  }

  public List<LeaveAllocationResponse> getAllocatedLeavesOfAllEmployees()
  {
    return leaveAllocationResponseBuilder
        .buildFromResult(leaveAllocationRepository.findAll());
  }

  private boolean isStartAndEndDateAreOverlappingWithExistingRecordForGivenEmployeeAndLeaveTypeId(
      final Long employeeId, final Long leaveTypeId, final Date startDate, final Date endDate)
      throws EntityAlreadyExistsException
  {
    List<LeaveAllocation> leaveAllocationList = leaveAllocationRepository
        .findAllByStartDateGreaterThanEqualAndStartDateLessThanEqual(startDate, endDate);

    leaveAllocationList.addAll(
        leaveAllocationRepository
            .findAllByEndDateGreaterThanEqualAndEndDateLessThanEqual(startDate, endDate));
    leaveAllocationList.addAll(leaveAllocationRepository.findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(startDate,endDate));

    Optional<LeaveAllocation>  leaveApplicationResult = leaveAllocationList
        .stream()
        .filter(leaveApplication -> (leaveApplication.getEmployee().getId() == employeeId) && (leaveApplication.getLeaveType().getId() == leaveTypeId))
        .findFirst();

    if (leaveApplicationResult.isPresent())
    {
      throw new EntityAlreadyExistsException(
          ErrorMessageConstants.EMPLOYEE_LEAVE_ALLOCATED_WITH_GIVEN_LEAVE_TYPE_AND_START_DATE_AND_END_DATE_OVERLAPPING);
    }
    else
    {
      return true;
    }
  }
}
