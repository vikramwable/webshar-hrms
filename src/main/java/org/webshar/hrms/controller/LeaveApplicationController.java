package org.webshar.hrms.controller;

import java.util.Collections;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.webshar.hrms.model.db.LeaveApplication;
import org.webshar.hrms.request.employee.leave.application.EmployeeLeaveApplicationCreateRequest;
import org.webshar.hrms.request.employee.leave.application.EmployeeLeaveApplicationUpdateRequest;
import org.webshar.hrms.response.BatchResponse;
import org.webshar.hrms.response.Response;
import org.webshar.hrms.service.LeaveApplicationService;
import org.webshar.hrms.service.exception.EntityAlreadyExistsException;
import org.webshar.hrms.service.exception.EntityNotFoundException;
import org.webshar.hrms.service.exception.InsufficientLeaveException;
import org.webshar.hrms.service.exception.ServiceException;

@RestController
@RequestMapping("/")
public class LeaveApplicationController
{

  @Autowired
  LeaveApplicationService leaveApplicationService;

  private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

  @GetMapping(value = "/services/api/web/hrms/leave-applications")
  public ResponseEntity<BatchResponse> getAllAppliedLeavesOfAnEmployeeByEmployeeIdLeaveTypeAndLeaveStatus(
      @RequestParam Long employee_id,
      @RequestParam(required = false) Long  leave_type_id,
      @RequestParam(required = false) Long leave_status_id)
      throws ServiceException
  {
    BatchResponse batchResponse = new BatchResponse();
    List<LeaveApplication> leaveList;
    leaveList = leaveApplicationService
        .getLeaveApplicationByEmployeeIdLeaveTypeAndLeaveStatus(employee_id, leave_type_id,
            leave_status_id);
    batchResponse.setEntities(Collections.singletonList(leaveList));
    batchResponse.setTotalEntries(leaveList.size());
    batchResponse.setMessage("Employee's applied leaves are fetched");
    batchResponse.setStatus("OK");
    return ResponseEntity.ok(batchResponse);
  }

  @PostMapping(value = "/services/api/web/hrms/leave-applications")
  public ResponseEntity<Response> applyLeaveOfAnEmployee(
      @Valid @RequestBody @NotNull EmployeeLeaveApplicationCreateRequest employeeLeaveApplicationCreateRequest)
      throws ServiceException
  {
    Response response = new Response();
    LeaveApplication  leaveApplication= leaveApplicationService.applyLeave(
        employeeLeaveApplicationCreateRequest);
    response.setEntity(leaveApplication);
    response.setMessage("Leave applied successfully");
    response.setStatus("OK");
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PatchMapping(value = "/services/api/web/hrms/leave-applications")
  public ResponseEntity<Response> updateAppliedLeaveOfAnEmployee(
      @Valid @RequestBody @NotNull EmployeeLeaveApplicationUpdateRequest employeeLeaveApplicationUpdateRequest)
      throws EntityNotFoundException
  {
    Response response = new Response();
    LeaveApplication leaveApplication = leaveApplicationService.updateLeaveApplication(employeeLeaveApplicationUpdateRequest);
    response.setEntity(leaveApplication);
    response.setMessage("Employee leave application is updated");
    response.setStatus("OK");
    return ResponseEntity.ok(response);
  }

  @GetMapping(value = "/services/api/web/hrms/leave-applications/all")
  public ResponseEntity<BatchResponse> getAllAppliedLeavesOfAllEmployees()
  {
    BatchResponse batchResponse = new BatchResponse();
    List<LeaveApplication> leaveApplicationList;
    leaveApplicationList = leaveApplicationService.getAllAppliedLeavesOfAllEmployees();
    batchResponse.setEntities(Collections.singletonList(leaveApplicationList));
    batchResponse.setTotalEntries(leaveApplicationList.size());
    batchResponse.setMessage("All leave applications are fetched");
    batchResponse.setStatus("OK");
    return ResponseEntity.ok(batchResponse);
  }

  @ExceptionHandler(EntityAlreadyExistsException.class)
  public ResponseEntity<Response> handleException(EntityAlreadyExistsException e)
  {
    LOGGER.error(e.getMessage(), e);
    Response response = new Response();
    response.setStatus("Already exists");
    response.setMessage(e.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Response> handleException(EntityNotFoundException e)
  {
    LOGGER.error(e.getMessage(), e);
    Response response = new Response();
    response.setStatus("Entity Not found");
    response.setMessage(e.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  @ExceptionHandler(InsufficientLeaveException.class)
  public ResponseEntity<Response> handleException(InsufficientLeaveException e)
  {
    LOGGER.error(e.getMessage(), e);
    Response response = new Response();
    response.setStatus("Insufficient leaves are available");
    response.setMessage(e.getMessage());
    return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(response);
  }
}
