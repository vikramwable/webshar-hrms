package org.webshar.hrms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.webshar.hrms.request.employee.leave.application.EmployeeLeaveApplicationCreateRequest;
import org.webshar.hrms.request.employee.leave.application.EmployeeLeaveApplicationUpdateRequest;
import org.webshar.hrms.response.employee.leave.application.EmployeeLeaveApplicationResponse;
import org.webshar.hrms.service.LeaveApplicationService;
import org.webshar.hrms.service.exception.EntityNotFoundException;
import org.webshar.hrms.service.exception.ServiceException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/leave-applications")
public class LeaveApplicationController
{

  @Autowired
  private LeaveApplicationService leaveApplicationService;

  private static final Logger LOGGER = LoggerFactory.getLogger(LeaveApplicationController.class);

  @GetMapping(value = "", params = {"employeeId", "leaveTypeId", "leaveStatusId"})
  public List<EmployeeLeaveApplicationResponse> getAllAppliedLeavesOfAnEmployeeByEmployeeIdLeaveTypeAndLeaveStatus(
      @RequestParam final Long employeeId,
      @RequestParam(required = false) final Long leaveTypeId,
      @RequestParam(required = false) final Long leaveStatusId)
      throws ServiceException
  {
    return leaveApplicationService.getLeaveApplicationByEmployeeIdLeaveTypeAndLeaveStatus(
            employeeId, leaveTypeId, leaveStatusId);
  }

  @PostMapping(value = "")
  @ResponseStatus(HttpStatus.CREATED)
  public EmployeeLeaveApplicationResponse applyLeaveOfAnEmployee(
      @Valid @RequestBody @NotNull final EmployeeLeaveApplicationCreateRequest employeeLeaveApplicationCreateRequest)
      throws ServiceException
  {
    return leaveApplicationService.applyLeave(employeeLeaveApplicationCreateRequest);
  }

  @PatchMapping(value = "/{id}")
  public EmployeeLeaveApplicationResponse updateAppliedLeaveOfAnEmployee(
      @PathVariable Long id,
      @Valid @RequestBody @NotNull final EmployeeLeaveApplicationUpdateRequest employeeLeaveApplicationUpdateRequest)
      throws EntityNotFoundException
  {
    Assert.isTrue(id.equals(employeeLeaveApplicationUpdateRequest.getId()),
            "id and employeeLeaveApplicationUpdateRequest.id must be same");
    return leaveApplicationService.updateLeaveApplication(employeeLeaveApplicationUpdateRequest);
  }

  @GetMapping(value = "")
  public List<EmployeeLeaveApplicationResponse> getAllAppliedLeavesOfAllEmployees()
  {
    return leaveApplicationService.getAllAppliedLeavesOfAllEmployees();
  }
}
