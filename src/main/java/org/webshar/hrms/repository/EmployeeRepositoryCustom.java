package org.webshar.hrms.repository;

import java.util.List;
import org.webshar.hrms.model.db.Employee;
import org.webshar.hrms.request.employee.EmployeeSearchRequest;

public interface EmployeeRepositoryCustom {

  List<Employee> searchEmployee(final EmployeeSearchRequest employeeSearchRequest);
}
