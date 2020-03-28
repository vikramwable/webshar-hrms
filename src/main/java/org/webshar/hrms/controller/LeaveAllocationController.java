package org.webshar.hrms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.webshar.hrms.request.employee.leave.allocation.EmployeeLeaveAllocationCreateRequest;
import org.webshar.hrms.request.employee.leave.allocation.EmployeeLeaveAllocationUpdateRequest;
import org.webshar.hrms.response.employee.leave.allocation.LeaveAllocationResponse;
import org.webshar.hrms.service.LeaveAllocationService;
import org.webshar.hrms.service.exception.ServiceException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/leave-allocations")
public class LeaveAllocationController
{

  @Autowired
  LeaveAllocationService leaveAllocationService;

  private static final Logger LOGGER = LoggerFactory.getLogger(LeaveAllocationController.class);

  @GetMapping(value = "")
  public List<LeaveAllocationResponse> getAllocatedLeavesOfAllEmployees()
  {
    return leaveAllocationService.getAllocatedLeavesOfAllEmployees();
  }

  @GetMapping(value = "", params = "employeeId")
  public List<LeaveAllocationResponse> getAllocatedLeavesOfAnEmployee(
      @RequestParam("employeeId") final long employeeId)
      throws ServiceException
  {
    return leaveAllocationService.getEmployeesAllocatedLeavesByEmployeeId(employeeId);
  }

  @PostMapping(value = "")
  @ResponseStatus(HttpStatus.CREATED)
  public LeaveAllocationResponse allocateLeavesToAnEmployee(
      @Valid @RequestBody @NotNull final EmployeeLeaveAllocationCreateRequest employeeLeaveAllocationCreateRequest)
      throws ServiceException
  {
    return leaveAllocationService.assignLeavesToAnEmployee(employeeLeaveAllocationCreateRequest);
  }

  @PatchMapping(value = "/{id}")
  public LeaveAllocationResponse updateAllocatedLeavesOfAnEmployeeByEmployeeIdAndLeaveTypeId(
      @PathVariable Long id,
      @NotNull @Valid @RequestBody final
      EmployeeLeaveAllocationUpdateRequest employeeLeaveAllocationUpdateRequest)
      throws ServiceException
  {
    Assert.isTrue(id.equals(employeeLeaveAllocationUpdateRequest.getId()),
        "id and employeeLeaveAllocationUpdateRequest.id must be same");
    return leaveAllocationService
        .updateEmployeesAllocatedLeavesByEmployeeIdAndLeaveType(
            employeeLeaveAllocationUpdateRequest);
  }

  @DeleteMapping(value = "", params = {"employeeId", "leaveTypeId"})
  public void deleteAllocatedLeavesOfAnEmployeeByEmployeeIdAndLeaveTypeId(
      @RequestParam(required = true) final Long employeeId,
      @RequestParam(required = false) final Long leaveTypeId)
      throws ServiceException
  {
    leaveAllocationService
        .deleteAllocatedLeavesOfAnEmployeeByEmployeeIdAndLeaveType(employeeId, leaveTypeId);
  }

}
