package org.webshar.hrms.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webshar.hrms.constants.ErrorMessageConstants;
import org.webshar.hrms.model.builder.EmployeeBuilder;
import org.webshar.hrms.model.db.Employee;
import org.webshar.hrms.model.db.Organization;
import org.webshar.hrms.repository.EmployeeRepository;
import org.webshar.hrms.request.employee.EmployeeCreateRequest;
import org.webshar.hrms.request.employee.EmployeeUpdateRequest;
import org.webshar.hrms.service.exception.EntityAlreadyExistsException;
import org.webshar.hrms.service.exception.EntityNotFoundException;

@Service
public class EmployeeService
{

  @Autowired
  OrganizationService organizationService;

  @Autowired
  EmployeeRepository employeeRepository;

  @Autowired
  EmployeeBuilder employeeBuilder;

  public Employee getEmployeeById(Long id) throws EntityNotFoundException
  {
    return employeeRepository.findById(id)
        .orElseThrow(
            () -> new EntityNotFoundException(ErrorMessageConstants.EMPLOYEE_BY_ID_NOT_FOUND));
  }


  public Employee getEmployeeByEmployeeId(Long employeeId) throws EntityNotFoundException
  {
    Employee employee = employeeRepository.findByEmployeeId(
        employeeId);
    if(employee == null)
        throw new EntityNotFoundException(ErrorMessageConstants.EMPLOYEE_BY_ID_NOT_FOUND);
    else
      return employee;
  }

  public Employee createEmployee(EmployeeCreateRequest employeeCreateRequest)
      throws EntityAlreadyExistsException, EntityNotFoundException

  {
    Organization organization = organizationService.getOrganizationById(employeeCreateRequest.getOrganizationId());

    List<Employee> employees = employeeRepository
        .findByEmail(employeeCreateRequest.getEmail());
    if (employees.isEmpty())
    {
      Employee employeeToCreate = employeeBuilder
          .buildFromRequest(employeeCreateRequest);
      return employeeRepository.save(employeeToCreate);
    }
    else
    {
      throw new EntityAlreadyExistsException(ErrorMessageConstants.EMPLOYEE_DUPLICATE_EMAIL);
    }
  }

  public Employee updateEmployee(EmployeeUpdateRequest employeeUpdateRequest)
      throws EntityNotFoundException
  {
    Optional<Employee> employeeToUpdate = employeeRepository
        .findById(employeeUpdateRequest.getId());

    if (employeeToUpdate.isPresent())
    {
      Employee updatedEmployee = employeeBuilder
          .buildFromRequest(employeeToUpdate.get(), employeeUpdateRequest);
      updatedEmployee = employeeRepository.save(updatedEmployee);
      return updatedEmployee;
    }
    else
    {
      throw new EntityNotFoundException(ErrorMessageConstants.EMPLOYEE_BY_ID_NOT_FOUND);
    }
  }

  public void deleteEmployeeByEmployeeId(Long employeeId) throws EntityNotFoundException
  {
    Employee employeeToDelete = getEmployeeByEmployeeId(employeeId);

    employeeRepository.deleteById(employeeToDelete.getId());
  }

  public List<Employee> getAllEmployees()
  {
    return employeeRepository.findAll();
  }
}
