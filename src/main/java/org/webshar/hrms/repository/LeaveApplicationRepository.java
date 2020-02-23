package org.webshar.hrms.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.webshar.hrms.model.db.LeaveApplication;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Long>
{
  public  LeaveApplication findByEmployeeIdAndStartDateAndEndDate(
      @Param("employee_id") Long employee_id,
      @Param("start_date") Date start_date,
      @Param("end_date") Date end_date
  );

  public List<LeaveApplication> findByEmployeeId(@Param("employee_id") Long employee_id);

  public List<LeaveApplication> findByEmployeeIdAndLeaveTypeId(@Param("employee_id") Long employee_id,
      @Param("leave_type_id") Long leave_type_id);

  public List<LeaveApplication> findByEmployeeIdAndLeaveTypeIdAndLeaveStatusId(
      @Param("employee_id") Long employee_id,
      @Param("leave_type_id") Long leave_type_id,
      @Param("leave_status_id") Long leave_status_id
  );

  public List<LeaveApplication> findByEmployeeIdAndLeaveTypeIdAndLeaveStatusIdNotIn(
      @Param("employee_id") Long employee_id,
      @Param("leave_type_id") Long leave_type_id,
      @Param("leave_status_id") Collection<Long> leave_status_ids
  );

}
