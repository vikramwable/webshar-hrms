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
  EmployeeService employeeService;

  @Autowired
  LeaveTypeService leaveTypeService;

  public LeaveAllocation getEmployeeAllocatedLeavesById(Long id)
      throws EntityNotFoundException
  {
    return leaveAllocationRepository.findById(id)
        .orElseThrow(
            () -> new EntityNotFoundException(
                ErrorMessageConstants.EMPLOYEE_LEAVE_BY_ID_NOT_FOUND));
  }

  public List<LeaveAllocation> getEmployeesAllocatedLeavesByEmployeeId(Long employeeId)
      throws EntityNotFoundException
  {
    return leaveAllocationRepository
        .findByEmployeeId(employeeService.getEmployeeByEmployeeId(employeeId).getId());
  }

  public LeaveAllocation getEmployeeAllocatedLeavesByEmployeeIdAndLeaveType(Long employeeId,
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

  public LeaveAllocation assignLeavesToAnEmployee(
      EmployeeLeaveAllocationCreateRequest employeeLeaveAllocationCreateRequest)
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
    return leaveAllocationRepository.save(
        leaveAllocationToCreate);
  }

  public LeaveAllocation updateEmployeesAllocatedLeavesByEmployeeIdAndLeaveType(
      EmployeeLeaveAllocationUpdateRequest employeeLeaveAllocationUpdateRequest)
      throws EntityNotFoundException, EntityAlreadyExistsException
  {
    Employee employee = employeeService
        .getEmployeeByEmployeeId(employeeLeaveAllocationUpdateRequest.getEmployeeId());
    LeaveType leaveType = leaveTypeService
        .getLeaveTypeById(employeeLeaveAllocationUpdateRequest.getLeaveTypeId());

    Optional<LeaveAllocation> employeeLeaveAllocationToUpdate =
        leaveAllocationRepository
            .findById(employeeLeaveAllocationUpdateRequest.getId());
    if (employeeLeaveAllocationToUpdate.isPresent())
    {
      LeaveAllocation updatedLeaveAllocation =
          leaveAllocationBuilder.buildFromRequest(employeeLeaveAllocationUpdateRequest,
              employeeLeaveAllocationToUpdate.get(), employee, leaveType);

      return leaveAllocationRepository.save(updatedLeaveAllocation);
    }
    else
    {
      throw new EntityNotFoundException(
          ErrorMessageConstants.EMPLOYEE_LEAVE_ALLOCATED_BY_ID_NOT_FOUND);
    }
  }

  public void deleteAllocatedLeavesOfAnEmployeeByEmployeeId(Long employeeId)
      throws EntityNotFoundException
  {
    leaveAllocationRepository
        .deleteByEmployeeId(employeeService.getEmployeeByEmployeeId(employeeId).getId());
  }

  public void deleteAllocatedLeavesOfAnEmployeeByEmployeeIdAndLeaveType(Long employeeId,
      Long leaveTypeId)
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

  public List<LeaveAllocation> getAllocatedLeavesOfAllEmployees()
  {
    return leaveAllocationRepository.findAll();

  }

  private boolean isStartAndEndDateAreOverlappingWithExistingRecordForGivenEmployeeAndLeaveTypeId(
      Long employeeId, Long leaveTypeId, Date startDate, Date endDate)
      throws EntityAlreadyExistsException
  {
    List<LeaveAllocation> leaveAllocationList = leaveAllocationRepository
        .findAllByEmployeeIdAndLeaveTypeIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            employeeId, leaveTypeId, startDate, endDate);

    leaveAllocationList.addAll(
        leaveAllocationRepository
            .findAllByEmployeeIdAndLeaveTypeIdAndStartDateGreaterThanEqualAndEndDateLessThanEqual(
                employeeId, leaveTypeId, startDate, endDate));

    if (!leaveAllocationList.isEmpty())
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
