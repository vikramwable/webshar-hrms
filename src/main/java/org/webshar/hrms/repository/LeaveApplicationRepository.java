package org.webshar.hrms.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.webshar.hrms.model.db.LeaveApplication;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Long>
{

  public LeaveApplication findByEmployeeIdAndStartDateAndEndDate(
      @Param("employee_id") final Long employeeId,
      @Param("start_date") final Date startDate,
      @Param("end_date") final Date endDate
  );

  public List<LeaveApplication> findByEmployeeId(@Param("employee_id") final Long employeeId);

  public List<LeaveApplication> findByEmployeeIdAndLeaveTypeId(
      @Param("employee_id") final Long employeeId,
      @Param("leave_type_id") final Long leaveTypeId);

  public List<LeaveApplication> findByEmployeeIdAndLeaveTypeIdAndLeaveStatusId(
      @Param("employee_id") final Long employeeId,
      @Param("leave_type_id") final Long leaveTypeId,
      @Param("leave_status_id") final Long leaveStatusId
  );

  public List<LeaveApplication> findByEmployeeIdAndLeaveTypeIdAndLeaveStatusIdNotIn(
      @Param("employee_id") final Long employeeId,
      @Param("leave_type_id") final Long leaveTypeId,
      @Param("leave_status_id") final Collection<Long> leaveStatusIds
  );

  public List<LeaveApplication> findAllByStartDateGreaterThanEqualAndStartDateLessThanEqual(
      @Param("start_date") final Date startDate,
      @Param("end_date") final Date endDate
  );

  public List<LeaveApplication> findAllByEndDateGreaterThanEqualAndEndDateLessThanEqual(
      @Param("start_date") final Date startDate,
      @Param("end_date") final Date endDate
  );

  public List<LeaveApplication> findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(
      @Param("start_date") final Date startDate,
      @Param("end_date") final Date endDate
  );
}
