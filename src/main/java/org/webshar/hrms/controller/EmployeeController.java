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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.webshar.hrms.model.db.Employee;
import org.webshar.hrms.request.employee.EmployeeCreateRequest;
import org.webshar.hrms.request.employee.EmployeeSearchRequest;
import org.webshar.hrms.request.employee.EmployeeUpdateRequest;
import org.webshar.hrms.service.EmployeeService;
import org.webshar.hrms.service.exception.ServiceException;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

  @Autowired
  EmployeeService employeeService;

  @GetMapping(value = "/{id}")
  public Employee getEmployeeById(
      @PathVariable Long id)
      throws ServiceException {
    return employeeService.getEmployeeById(id);
  }

  @PostMapping(value = "")
  @ResponseStatus(HttpStatus.CREATED)
  public Employee createEmployee(
      @Valid @RequestBody @NotNull EmployeeCreateRequest employeeCreateRequest)
      throws ServiceException {
    return employeeService.createEmployee(employeeCreateRequest);
  }

  @PostMapping(value = "/search")
  public List<Employee> searchEmployee(
      @Valid @RequestBody @NotNull EmployeeSearchRequest employeeSearchRequest) {
    return employeeService.searchEmployee(employeeSearchRequest);
  }

  @PatchMapping(value = "/{id}")
  public Employee updateEmployeeById(
      @PathVariable Long id,
      @NotNull @Valid @RequestBody EmployeeUpdateRequest employeeUpdateRequest) throws ServiceException {
    return employeeService.updateEmployee(id, employeeUpdateRequest);
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteEmployeeById(@PathVariable("id") Long id) throws ServiceException {
    employeeService.deleteEmployeeById(id);
  }

  @GetMapping(value = "")
  public List<Employee> getAllEmployees() {
    return employeeService.getAllEmployees();
  }
}
