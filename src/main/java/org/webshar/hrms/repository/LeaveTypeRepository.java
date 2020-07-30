package org.webshar.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.webshar.hrms.model.db.LeaveType;

@Repository
public interface LeaveTypeRepository extends JpaRepository<LeaveType, Long> {

}
