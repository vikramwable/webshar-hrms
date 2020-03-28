package org.webshar.hrms.controller;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.webshar.hrms.request.employee.leave.application.EmployeeLeaveApplicationCreateRequest;
import org.webshar.hrms.request.employee.leave.application.EmployeeLeaveApplicationUpdateRequest;
import org.webshar.hrms.response.employee.leave.application.EmployeeLeaveApplicationResponse;
import org.webshar.hrms.service.LeaveApplicationService;
import org.webshar.hrms.service.exception.EntityNotFoundException;
import org.webshar.hrms.service.exception.ServiceException;

@RestController
@RequestMapping("/api/leave-applications")
public class LeaveApplicationController {

  @Autowired
  private LeaveApplicationService leaveApplicationService;

  @GetMapping(value = "", params = {"employeeId", "leaveTypeId", "leaveStatusId"})
  public List<EmployeeLeaveApplicationResponse> getAllAppliedLeavesOfAnEmployeeByEmployeeIdLeaveTypeAndLeaveStatus(
      @RequestParam final Long employeeId,
      @RequestParam(required = false) final Long leaveTypeId,
      @RequestParam(required = false) final Long leaveStatusId)
      throws ServiceException {
    return leaveApplicationService.getLeaveApplicationByEmployeeIdLeaveTypeAndLeaveStatus(
        employeeId, leaveTypeId, leaveStatusId);
  }

  @PostMapping(value = "")
  @ResponseStatus(HttpStatus.CREATED)
  public EmployeeLeaveApplicationResponse applyLeaveOfAnEmployee(
      @Valid @RequestBody @NotNull final EmployeeLeaveApplicationCreateRequest employeeLeaveApplicationCreateRequest)
      throws ServiceException {
    return leaveApplicationService.applyLeave(employeeLeaveApplicationCreateRequest);
  }

  @PatchMapping(value = "/{id}")
  public EmployeeLeaveApplicationResponse updateAppliedLeaveOfAnEmployee(
      @PathVariable Long id,
      @Valid @RequestBody @NotNull final EmployeeLeaveApplicationUpdateRequest employeeLeaveApplicationUpdateRequest)
      throws EntityNotFoundException {
    return leaveApplicationService.updateLeaveApplication(id, employeeLeaveApplicationUpdateRequest);
  }

  @GetMapping(value = "")
  public List<EmployeeLeaveApplicationResponse> getAllAppliedLeavesOfAllEmployees() {
    return leaveApplicationService.getAllAppliedLeavesOfAllEmployees();
  }
}
