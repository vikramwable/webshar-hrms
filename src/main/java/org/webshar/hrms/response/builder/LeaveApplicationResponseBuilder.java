package org.webshar.hrms.response.builder;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;
import org.webshar.hrms.model.db.LeaveApplication;
import org.webshar.hrms.response.employee.leave.application.EmployeeLeaveApplicationResponse;

@Component
public class LeaveApplicationResponseBuilder
{

  public EmployeeLeaveApplicationResponse buildFromResult(LeaveApplication leaveApplication)
  {
    ModelMapper modelMapper = new ModelMapper();
    EmployeeLeaveApplicationResponse employeeLeaveApplicationResponse = modelMapper
        .map(leaveApplication, EmployeeLeaveApplicationResponse.class);
    return employeeLeaveApplicationResponse;
  }

  public List<EmployeeLeaveApplicationResponse> buildFromResult(List<LeaveApplication> leaveApplicationList)
  {
    ModelMapper modelMapper = new ModelMapper();
    List<EmployeeLeaveApplicationResponse> employeeLeaveApplicationResponseList = modelMapper
        .map(leaveApplicationList, new TypeToken<List<EmployeeLeaveApplicationResponse>>(){}.getType());
    return employeeLeaveApplicationResponseList;
  }


}
