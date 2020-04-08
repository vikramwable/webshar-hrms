package org.webshar.hrms.request.builder.employee.leave.allocatiion;

import java.time.LocalDate;
import org.springframework.stereotype.Component;
import org.webshar.hrms.model.db.Employee;
import org.webshar.hrms.model.db.OrganizationLeave;
import org.webshar.hrms.request.employee.leave.allocation.EmployeeLeaveAllocationCreateRequest;

@Component
public class EmployeeLeaveAllocationRequestBuilder
{
  public static final String START_DATE = "startDate";
  public static final String END_DATE = "endDate";

  public EmployeeLeaveAllocationCreateRequest buildFromOrganizationLeaves(Employee employee, OrganizationLeave organizationLeave){

    //For each assigned leaveType allocate same leaves to employee
    EmployeeLeaveAllocationCreateRequest employeeLeaveAllocationCreateRequest = new EmployeeLeaveAllocationCreateRequest();
    employeeLeaveAllocationCreateRequest.setEmployeeId(employee.getId());
    //Additional and Carried leaves of current leaveType are zero as employee first time created
    employeeLeaveAllocationCreateRequest.setAdditionalLeaves(0L);
    employeeLeaveAllocationCreateRequest.setCarriedLeaves(0L);
    employeeLeaveAllocationCreateRequest.setAllottedLeaves(organizationLeave.getCount());
    employeeLeaveAllocationCreateRequest.setLeaveTypeId(organizationLeave.getLeaveType().getId());
    employeeLeaveAllocationCreateRequest.setStartDate(LocalDate.parse(getDate(START_DATE)));
    employeeLeaveAllocationCreateRequest.setEndDate(LocalDate.parse(getDate(END_DATE)));

    return employeeLeaveAllocationCreateRequest;
  }

  private String getDate(String whichDate){
    LocalDate currentdate = LocalDate.now();
    int currentYear = currentdate.getYear();
    switch (whichDate){
      case START_DATE:
        return currentYear + "-" + "01" + "-" + "01";
      case END_DATE:
        return currentYear + "-" + "12" + "-" + "31";
      default:
        return null;
    }
  }
}
