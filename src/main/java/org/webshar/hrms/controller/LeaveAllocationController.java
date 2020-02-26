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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.webshar.hrms.request.employee.leave.allocation.EmployeeLeaveAllocationCreateRequest;
import org.webshar.hrms.request.employee.leave.allocation.EmployeeLeaveAllocationUpdateRequest;
import org.webshar.hrms.response.BatchResponse;
import org.webshar.hrms.response.Response;
import org.webshar.hrms.response.employee.leave.allocation.LeaveAllocationResponse;
import org.webshar.hrms.service.LeaveAllocationService;
import org.webshar.hrms.service.exception.EntityAlreadyExistsException;
import org.webshar.hrms.service.exception.EntityNotFoundException;
import org.webshar.hrms.service.exception.InsufficientLeaveException;
import org.webshar.hrms.service.exception.ServiceException;

@RestController
@RequestMapping("/")
public class LeaveAllocationController
{

  @Autowired
  LeaveAllocationService leaveAllocationService;

  private static final Logger LOGGER = LoggerFactory.getLogger(LeaveAllocationController.class);

  @GetMapping(value = "/services/api/web/hrms/leave-allocations/all")
  public ResponseEntity<BatchResponse> getAllocatedLeavesOfAllEmployees()
  {
    BatchResponse batchResponse = new BatchResponse();
    List<LeaveAllocationResponse> leaveAllocationResponseList;
    leaveAllocationResponseList = leaveAllocationService.getAllocatedLeavesOfAllEmployees();
    batchResponse.setEntities(Collections.singletonList(leaveAllocationResponseList));
    batchResponse.setTotalEntries(leaveAllocationResponseList.size());
    batchResponse.setMessage("All allocated leaves of all the employees are fetched");
    batchResponse.setStatus("OK");
    return ResponseEntity.ok(batchResponse);
  }

  @GetMapping(value = "/services/api/web/hrms/leave-allocations")
  public ResponseEntity<BatchResponse> getAllocatedLeavesOfAnEmployee(
      @RequestParam("employee_id") final Long employee_id)
      throws ServiceException
  {
    BatchResponse batchResponse = new BatchResponse();
    List<LeaveAllocationResponse> leaveAllocationResponseList;
    leaveAllocationResponseList = leaveAllocationService
        .getEmployeesAllocatedLeavesByEmployeeId(employee_id);
    batchResponse.setEntities(Collections.singletonList(leaveAllocationResponseList));
    batchResponse.setTotalEntries(leaveAllocationResponseList.size());
    batchResponse.setMessage("Employees allocated leaves are fetched");
    batchResponse.setStatus("OK");
    return ResponseEntity.ok(batchResponse);
  }

  @PostMapping(value = "/services/api/web/hrms/leave-allocations")
  public ResponseEntity<Response> allocateLeavesToAnEmployee(
      @Valid @RequestBody @NotNull final EmployeeLeaveAllocationCreateRequest employeeLeaveAllocationCreateRequest)
      throws ServiceException
  {
    Response response = new Response();
    LeaveAllocationResponse leaveAllocationResponse = leaveAllocationService
        .assignLeavesToAnEmployee(employeeLeaveAllocationCreateRequest);
    response.setEntity(leaveAllocationResponse);
    response.setMessage("Leaves allocated to an employee");
    response.setStatus("OK");
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PatchMapping(value = "/services/api/web/hrms/leave-allocations")
  public ResponseEntity<Response> updateAllocatedLeavesOfAnEmployeeByEmployeeIdAndLeaveTypeId(
      @NotNull @Valid @RequestBody final
          EmployeeLeaveAllocationUpdateRequest employeeLeaveAllocationUpdateRequest)
      throws ServiceException
  {
    Response response = new Response();
    LeaveAllocationResponse leaveAllocationResponse = leaveAllocationService
        .updateEmployeesAllocatedLeavesByEmployeeIdAndLeaveType(
            employeeLeaveAllocationUpdateRequest);
    response.setEntity(leaveAllocationResponse);
    response.setMessage("Employee allocated leaves are updated");
    response.setStatus("OK");
    return ResponseEntity.ok(response);
  }

  @DeleteMapping(value = "/services/api/web/hrms/leave-allocations")
  public ResponseEntity<Void> deleteAllocatedLeavesOfAnEmployeeByEmployeeIdAndLeaveTypeId(
      @RequestParam("employee_id") final Long employeeId,
      @RequestParam(required = false) final Long leaveTypeId)
      throws ServiceException
  {
    leaveAllocationService
        .deleteAllocatedLeavesOfAnEmployeeByEmployeeIdAndLeaveType(employeeId, leaveTypeId);
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
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
