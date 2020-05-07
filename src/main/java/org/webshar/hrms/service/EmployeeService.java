package org.webshar.hrms.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webshar.hrms.constants.ErrorMessageConstants;
import org.webshar.hrms.model.builder.EmployeeBuilder;
import org.webshar.hrms.model.db.Employee;
import org.webshar.hrms.model.db.OrganizationLeave;
import org.webshar.hrms.repository.EmployeeRepository;
import org.webshar.hrms.request.builder.employee.leave.allocatiion.EmployeeLeaveAllocationRequestBuilder;
import org.webshar.hrms.request.employee.EmployeeCreateRequest;
import org.webshar.hrms.request.employee.EmployeeSearchRequest;
import org.webshar.hrms.request.employee.EmployeeUpdateRequest;
import org.webshar.hrms.service.exception.BadRequestException;
import org.webshar.hrms.service.exception.EntityAlreadyExistsException;
import org.webshar.hrms.service.exception.EntityNotFoundException;

@Service
public class EmployeeService {

  @Autowired
  EmployeeRepository employeeRepository;

  @Autowired
  EmployeeBuilder employeeBuilder;

  @Autowired
  OrganizationService organizationService;

  @Autowired
  OrganizationLeaveService organizationLeaveService;

  @Autowired
  LeaveAllocationService leaveAllocationService;

  @Autowired
  EmployeeLeaveAllocationRequestBuilder employeeLeaveAllocationRequestBuilder;

  public static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

  public Employee getEmployeeById(Long id) throws EntityNotFoundException {
    return employeeRepository.findById(id)
        .orElseThrow(
            () -> new EntityNotFoundException(ErrorMessageConstants.EMPLOYEE_BY_ID_NOT_FOUND));
  }


  public Employee getEmployeeByEmployeeId(String employeeId) throws EntityNotFoundException {
    Employee employee = employeeRepository.findByEmpId(employeeId);
    if (employee == null) {
      throw new EntityNotFoundException(ErrorMessageConstants.EMPLOYEE_BY_ID_NOT_FOUND);
    } else {
      return employee;
    }
  }

  @Transactional
  public Employee createEmployee(EmployeeCreateRequest employeeCreateRequest)
      throws EntityAlreadyExistsException, BadRequestException, EntityNotFoundException {
    List<Employee> employees = employeeRepository
        .findByEmpIdOrEmail(employeeCreateRequest.getEmpId(), employeeCreateRequest.getEmail());
    Optional<Employee> reportTo = getReportsToEmployee(employeeCreateRequest.getReportsTo());
    organizationService.getOrganizationById(employeeCreateRequest.getOrganizationId());
    if (employees.isEmpty()) {
      Employee employeeToCreate = employeeBuilder
          .buildFromRequest(employeeCreateRequest, reportTo.isPresent() ? reportTo.get() : null);
      employeeToCreate = employeeRepository.save(employeeToCreate);
      assignDefaultInitialLeavesToEmployee(employeeToCreate);
      return employeeToCreate;
    } else {
      throw new EntityAlreadyExistsException(ErrorMessageConstants.EMPLOYEE_DUPLICATE_EMAIL);
    }
  }

  @Transactional
  public Employee updateEmployee(final Long id, EmployeeUpdateRequest employeeUpdateRequest)
      throws EntityNotFoundException, BadRequestException {
    Employee employeeToUpdate = employeeRepository.findById(id)
        .orElseThrow(
            () -> new EntityNotFoundException(ErrorMessageConstants.EMPLOYEE_BY_ID_NOT_FOUND));
    Optional<Employee> reportTo = getReportsToEmployee(employeeUpdateRequest.getReportsTo());
    Employee updatedEmployee = employeeBuilder
        .buildFromRequest(employeeToUpdate, employeeUpdateRequest,
            reportTo.isPresent() ? reportTo.get() : null);
    return employeeRepository.save(updatedEmployee);
  }

  private Optional<Employee> getReportsToEmployee(Long reportsTo) {
    Optional<Employee> reportTo = Optional.empty();
    if (reportsTo != null) {
      reportTo = employeeRepository.findById(reportsTo);
    }
    return reportTo;
  }

  public void deleteEmployeeById(Long id) throws EntityNotFoundException {
    Employee employeeToDelete = getEmployeeById(id);
    employeeRepository.deleteById(employeeToDelete.getId());
  }

  public List<Employee> getAllEmployees() {
    return employeeRepository.findAll();
  }

  @Transactional
  public List<Employee> searchEmployee(final EmployeeSearchRequest employeeSearchRequest) {
    return employeeRepository.searchEmployee(employeeSearchRequest);
  }

  private void assignDefaultInitialLeavesToEmployee(Employee employee)
      throws EntityNotFoundException, EntityAlreadyExistsException {
    //Fetch All assigned leaves of an employee's organization
    List<OrganizationLeave> organizationLeaveList = organizationLeaveService
        .getOrganizationLeaveByOrganizationId(employee.getOrganization().getId());

    if (!organizationLeaveList.isEmpty()) {
      for (OrganizationLeave organizationLeave : organizationLeaveList) {
        leaveAllocationService.assignLeavesToAnEmployee(employeeLeaveAllocationRequestBuilder
            .buildFromOrganizationLeaves(employee, organizationLeave));
      }
    }
  }
}
