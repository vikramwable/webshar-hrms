package org.webshar.hrms.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.webshar.hrms.model.db.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, EmployeeRepositoryCustom {

  List<Employee> findByEmail(@Param("email") String email);

  Employee findByEmployeeId(@Param("employee_id") Long employeeId);

  boolean existsByIdAndOrganizationId(@Param("employee_id") final Long employeeId,
      @Param("organization_id") final Long organizationId);

  Employee findByEmpId(@Param("emp_id") String empId);

  List<Employee> findByEmpIdOrEmail(@Param("emp_id") String empId,
      @Param("email") String email);
}
