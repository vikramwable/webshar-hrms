package org.webshar.hrms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.webshar.hrms.model.db.Employee;
import org.webshar.hrms.request.employee.EmployeeCreateRequest;
import org.webshar.hrms.request.employee.EmployeeUpdateRequest;
import org.webshar.hrms.service.EmployeeService;
import org.webshar.hrms.service.exception.ServiceException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController
{

  @Autowired
  EmployeeService employeeService;

  private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

  @GetMapping(value = "/{id}")
  public Employee getEmployeeByEmployeeId(
      @PathVariable Long id)
      throws ServiceException
  {
    return employeeService.getEmployeeById(id);
  }

  @PostMapping(value = "")
  @ResponseStatus(HttpStatus.CREATED)
  public Employee createEmployee(
      @Valid @RequestBody @NotNull EmployeeCreateRequest employeeCreateRequest)
      throws ServiceException
  {
    return employeeService.createEmployee(employeeCreateRequest);
  }

  @PatchMapping(value = "/{id}")
  public Employee updateEmployeeById(
      @PathVariable Long id,
      @NotNull @Valid @RequestBody
          EmployeeUpdateRequest employeeUpdateRequest) throws ServiceException
  {
    Assert.isTrue(id.equals(employeeUpdateRequest.getId()),
            "id and employeeUpdateRequest.id must be same");
    return employeeService.updateEmployee(employeeUpdateRequest);
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteEmployeeById(@PathVariable("id") Long id) throws ServiceException
  {
    employeeService.deleteEmployeeById(id);
  }

  @GetMapping(value = "")
  public List<Employee> getAllEmployees()
  {
    return employeeService.getAllEmployees();
  }
}
