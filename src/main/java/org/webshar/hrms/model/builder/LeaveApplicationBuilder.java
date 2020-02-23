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
      EmployeeLeaveApplicationCreateRequest employeeLeaveApplicationCreateRequest,
      LeaveType leaveType, Employee employee, LeaveStatus leaveStatus)
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
      LeaveApplication leaveApplicationToBeUpdated, Employee employee, LeaveStatus leaveStatus)
  {
    LeaveApplication leaveApplicationAfterUpdate = new LeaveApplication(
        leaveApplicationToBeUpdated);
    leaveApplicationAfterUpdate.setEmployee(employee);
    leaveApplicationAfterUpdate.setLeaveStatus(leaveStatus);
    return leaveApplicationAfterUpdate;
  }
}
