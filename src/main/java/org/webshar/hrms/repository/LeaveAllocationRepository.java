package org.webshar.hrms.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.webshar.hrms.model.db.LeaveAllocation;

public interface LeaveAllocationRepository extends
    JpaRepository<LeaveAllocation, Long> {

  public List<LeaveAllocation> findByEmployeeId(@Param("employee_id") final Long employee_id);

  public List<LeaveAllocation> findByEmployeeIdAndLeaveTypeIdAndStartDateAndEndDate(
      @Param("employee_id") final Long employeeId,
      @Param("leave_type_id") final Long leaveTypeId,
      @Param("start_date") final Date startDate,
      @Param("end_date") final Date endDate
  );

  public List<LeaveAllocation> findAllByStartDateGreaterThanEqualAndStartDateLessThanEqual(
      @Param("start_date") final Date startDate,
      @Param("end_date") final Date endDate
  );

  public List<LeaveAllocation> findAllByEndDateGreaterThanEqualAndEndDateLessThanEqual(
      @Param("start_date") final Date startDate,
      @Param("end_date") final Date endDate
  );

  public List<LeaveAllocation> findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(
      @Param("start_date") final Date startDate,
      @Param("end_date") final Date endDate
  );

  public LeaveAllocation findByEmployeeIdAndLeaveTypeId(
      @Param("employee_id") final Long employeeId,
      @Param("leave_type_id") final Long leaveTypeId
  );

  public void deleteByEmployeeId(@Param("employee_id") final Long employeeId);

  public void deleteByEmployeeIdAndLeaveTypeId(@Param("employee_id") final Long employeeId,
      @Param("leave_type_id") final Long leaveTypeId);

}
