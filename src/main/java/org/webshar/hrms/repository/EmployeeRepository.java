package org.webshar.hrms.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.webshar.hrms.model.db.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>
{

  List<Employee> findByEmail(@Param("email") String email);
}