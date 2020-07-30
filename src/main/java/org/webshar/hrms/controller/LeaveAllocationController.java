package org.webshar.hrms.controller;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.webshar.hrms.request.employee.leave.allocation.EmployeeLeaveAllocationCreateRequest;
import org.webshar.hrms.request.employee.leave.allocation.EmployeeLeaveAllocationUpdateRequest;
import org.webshar.hrms.response.employee.leave.allocation.LeaveAllocationResponse;
import org.webshar.hrms.service.LeaveAllocationService;
import org.webshar.hrms.service.exception.ServiceException;

@RestController
@RequestMapping("/api/leave-allocations")
public class LeaveAllocationController {

  @Autowired
  LeaveAllocationService leaveAllocationService;

  @GetMapping(value = "")
  public List<LeaveAllocationResponse> getAllocatedLeavesOfAllEmployees() {
    return leaveAllocationService.getAllocatedLeavesOfAllEmployees();
  }

  @GetMapping(value = "", params = "employeeId")
  public List<LeaveAllocationResponse> getAllocatedLeavesOfAnEmployee(
      @RequestParam("employeeId") final Long employeeId) {
    return leaveAllocationService.getEmployeesAllocatedLeavesByEmployeeId(employeeId);
  }

  @PostMapping(value = "")
  @ResponseStatus(HttpStatus.CREATED)
  public LeaveAllocationResponse allocateLeavesToAnEmployee(
      @Valid @RequestBody @NotNull final EmployeeLeaveAllocationCreateRequest employeeLeaveAllocationCreateRequest)
      throws ServiceException {
    return leaveAllocationService.assignLeavesToAnEmployee(employeeLeaveAllocationCreateRequest);
  }

  @PatchMapping(value = "/{id}")
  public LeaveAllocationResponse updateAllocatedLeavesOfAnEmployeeByEmployeeIdAndLeaveTypeId(
      @PathVariable Long id,
      @NotNull @Valid @RequestBody final
      EmployeeLeaveAllocationUpdateRequest employeeLeaveAllocationUpdateRequest)
      throws ServiceException {
    return leaveAllocationService
        .updateEmployeesAllocatedLeavesByEmployeeIdAndLeaveType(id, employeeLeaveAllocationUpdateRequest);
  }

  @DeleteMapping(value = "", params = {"employeeId", "leaveTypeId"})
  public void deleteAllocatedLeavesOfAnEmployeeByEmployeeIdAndLeaveTypeId(
      @RequestParam(required = true) final Long employeeId,
      @RequestParam(required = false) final Long leaveTypeId)
      throws ServiceException {
    leaveAllocationService
        .deleteAllocatedLeavesOfAnEmployeeByEmployeeIdAndLeaveType(employeeId, leaveTypeId);
  }

}
