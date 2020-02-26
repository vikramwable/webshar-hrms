package org.webshar.hrms.response.builder;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;
import org.webshar.hrms.model.db.LeaveAllocation;
import org.webshar.hrms.response.employee.leave.allocation.LeaveAllocationResponse;

@Component
public class LeaveAllocationResponseBuilder
{

  public LeaveAllocationResponse buildFromResult(LeaveAllocation leaveAllocation)
  {
    ModelMapper modelMapper = new ModelMapper();
    LeaveAllocationResponse leaveAllocationResponse = modelMapper
        .map(leaveAllocation, LeaveAllocationResponse.class);
    return leaveAllocationResponse;
  }

  public List<LeaveAllocationResponse> buildFromResult(List<LeaveAllocation> leaveAllocationList)
  {
    ModelMapper modelMapper = new ModelMapper();
    List<LeaveAllocationResponse> leaveAllocationResponseList = modelMapper
        .map(leaveAllocationList, new TypeToken<List<LeaveAllocationResponse>>(){}.getType());
    return leaveAllocationResponseList;
  }
}
