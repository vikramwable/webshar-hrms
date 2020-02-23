package org.webshar.hrms.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.webshar.hrms.model.db.LeaveAllocation;

public interface LeaveAllocationRepository extends
    JpaRepository<LeaveAllocation, Long>
{

  public List<LeaveAllocation> findByEmployeeId(@Param("employee_id") Long employee_id);

  public List<LeaveAllocation> findByEmployeeIdAndLeaveTypeIdAndStartDateAndEndDate(
      @Param("employee_id") Long employee_id,
      @Param("leave_type_id") Long leave_type_id,
      @Param("start_date") Date start_date,
      @Param("end_date") Date end_date
  );

  public List<LeaveAllocation> findAllByEmployeeIdAndLeaveTypeIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
      @Param("employee_id") Long employee_id,
      @Param("leave_type_id") Long leave_type_id,
      @Param("start_date") Date start_date,
      @Param("end_date") Date end_date
  );

  public List<LeaveAllocation> findAllByEmployeeIdAndLeaveTypeIdAndStartDateGreaterThanEqualAndEndDateLessThanEqual(
      @Param("employee_id") Long employee_id,
      @Param("leave_type_id") Long leave_type_id,
      @Param("start_date") Date start_date,
      @Param("end_date") Date end_date
  );

  public LeaveAllocation findByEmployeeIdAndLeaveTypeId(
      @Param("employee_id") Long employee_id,
      @Param("leave_type_id") Long leave_type_id
  );

  public void deleteByEmployeeId(@Param("employee_id") Long employee_id);

  public void deleteByEmployeeIdAndLeaveTypeId(@Param("employee_id") Long employee_id,
      @Param("leave_type_id") Long leave_type_id);


}
