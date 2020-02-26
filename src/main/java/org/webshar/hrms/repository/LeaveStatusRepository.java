package org.webshar.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.webshar.hrms.model.db.LeaveStatus;

public interface LeaveStatusRepository extends JpaRepository<LeaveStatus,Long>
{
  public LeaveStatus findByStatus(@Param("status") String status);

}
