package org.webshar.hrms.controller;

import java.util.Collections;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.webshar.hrms.model.db.Employee;
import org.webshar.hrms.request.employee.EmployeeCreateRequest;
import org.webshar.hrms.request.employee.EmployeeUpdateRequest;
import org.webshar.hrms.response.BatchResponse;
import org.webshar.hrms.response.Response;
import org.webshar.hrms.service.EmployeeService;
import org.webshar.hrms.service.exception.EntityAlreadyExistsException;
import org.webshar.hrms.service.exception.EntityNotFoundException;
import org.webshar.hrms.service.exception.InsufficientLeaveException;
import org.webshar.hrms.service.exception.ServiceException;

@RestController
@RequestMapping("/")
public class EmployeeController
{

  @Autowired
  EmployeeService employeeService;

  private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

  @GetMapping(value = "/services/api/web/hrms/employees/{employee_id}")
  public ResponseEntity<Response> getEmployeeById(
      @PathVariable("employee_id") Long employeeId)
      throws ServiceException
  {
    Response response = new Response();
    Employee employee = employeeService.getEmployeeById(employeeId);
    response.setEntity(employee);
    response.setMessage("Employee fetched");
    response.setStatus("OK");
    return ResponseEntity.ok(response);
  }

  @PostMapping(value = "/services/api/web/hrms/employees")
  public ResponseEntity<Response> createEmployee(
      @Valid @RequestBody @NotNull EmployeeCreateRequest employeeCreateRequest)
      throws ServiceException
  {
    Response response = new Response();
    Employee employee = employeeService.createEmployee(employeeCreateRequest);
    response.setEntity(employee);
    response.setMessage("Employee added");
    response.setStatus("OK");
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PatchMapping(value = "/services/api/web/hrms/employees")
  public ResponseEntity<Response> updateEmployeeById(
      @NotNull @Valid @RequestBody
          EmployeeUpdateRequest employeeUpdateRequest) throws ServiceException
  {
    Response response = new Response();
    Employee employee = employeeService.updateEmployee(employeeUpdateRequest);
    response.setEntity(employee);
    response.setMessage("Employee updated");
    response.setStatus("OK");
    return ResponseEntity.ok(response);
  }

  @DeleteMapping(value = "/services/api/web/hrms/employees/{employee_id}")
  public ResponseEntity<Void> deleteEmployeeById(
      @PathVariable("employee_id") Long employeeId)
      throws ServiceException
  {
    employeeService.deleteEmployeeByEmployeeId(employeeId);
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }

  @GetMapping(value = "/services/api/web/hrms/employees")
  public ResponseEntity<BatchResponse> getAllEmployees()
  {
    BatchResponse batchResponse = new BatchResponse();
    List<Employee> employeesList;
    employeesList = employeeService.getAllEmployees();
    batchResponse.setEntities(Collections.singletonList(employeesList));
    batchResponse.setTotalEntries(employeesList.size());
    batchResponse.setMessage("Employees fetched");
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
