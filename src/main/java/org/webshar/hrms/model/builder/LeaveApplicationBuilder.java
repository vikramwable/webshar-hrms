package org.webshar.hrms.model.builder;

import org.springframework.stereotype.Component;
import org.webshar.hrms.model.db.Employee;
import org.webshar.hrms.model.db.LeaveApplication;
import org.webshar.hrms.model.db.LeaveStatus;
import org.webshar.hrms.model.db.LeaveType;
import org.webshar.hrms.request.employee.leave.application.EmployeeLeaveApplicationCreateRequest;

@Component
public class LeaveApplicationBuilder
{

  public LeaveApplication buildFromRequest(
      final EmployeeLeaveApplicationCreateRequest employeeLeaveApplicationCreateRequest,
      final LeaveType leaveType, final Employee employee, final LeaveStatus leaveStatus)
  {
    LeaveApplication leaveApplication = new LeaveApplication();
    leaveApplication.setLeaveType(leaveType);
    leaveApplication.setEmployee(employee);
    leaveApplication.setLeaveStatus(leaveStatus);
    leaveApplication.setEndDate(employeeLeaveApplicationCreateRequest.getEndDate());
    leaveApplication.setStartDate(employeeLeaveApplicationCreateRequest.getStartDate());
    return leaveApplication;
  }

  public LeaveApplication buildFromRequest(
      final LeaveApplication leaveApplicationToBeUpdated, final Employee employee,
      final LeaveStatus leaveStatus)
  {
    LeaveApplication leaveApplicationAfterUpdate = new LeaveApplication(
        leaveApplicationToBeUpdated);
    leaveApplicationAfterUpdate.setEmployee(employee);
    leaveApplicationAfterUpdate.setLeaveStatus(leaveStatus);
    return leaveApplicationAfterUpdate;
  }
}
