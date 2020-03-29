package org.webshar.hrms.request.employee.leave.allocation;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.webshar.hrms.constants.ErrorMessageConstants;

@Data
public class EmployeeLeaveAllocationCreateRequest {

  @NotNull(message = ErrorMessageConstants.EMPLOYEE_EMP_ID_NOT_NULL)
  private Long employeeId;

  @NotNull(message = ErrorMessageConstants.LEAVE_LEAVE_TYPE_ID_NOT_NULL)
  private Long leaveTypeId;

  @NotNull(message = ErrorMessageConstants.LEAVE_START_DATE_NOT_NULL)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate startDate;

  @NotNull(message = ErrorMessageConstants.LEAVE_END_DATE_NOT_NULL)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate endDate;

  @NotNull(message = ErrorMessageConstants.EMPLOYEE_LEAVE_ALLOTTED_LEAVES_NOT_NULL)
  private Long allottedLeaves;

  @NotNull(message = ErrorMessageConstants.EMPLOYEE_LEAVE_CARRIED_LEAVES_NOT_NULL)
  private Long carriedLeaves;

  @NotNull(message = ErrorMessageConstants.EMPLOYEE_LEAVE_ADDITIONAL_LEAVES_NOT_NULL)
  private Long additionalLeaves;

  @AssertTrue(message = ErrorMessageConstants.LEAVE_END_DATE_CANNOT_LESS_THAN_START_DATE)
  private boolean isValidEndDate()
  {
    if (endDate == null || endDate.isBefore(startDate) )
    {
      return false;
    }
    else
    {
      return true;
    }
  }

  @AssertTrue(message =
      ErrorMessageConstants.LEAVE_ALLOCATED_LEAVES_ARE_MORE_THAN_ALLOCATED_TIME_PERIOD)
  private boolean isAllottedLeavesCountValid()
  {
    if(allottedLeaves != null && allottedLeaves < 0)
    {
      return false;
    }
    else if(ChronoUnit.DAYS.between(startDate, endDate)+1 < allottedLeaves){
      return false;
    }
    return true;
  }

  @AssertTrue(message =
      ErrorMessageConstants.LEAVE_CARRIED_LEAVES_ARE_MORE_THAN_ALLOCATED_TIME_PERIOD)
  private boolean isCarriedLeavesCountValid()
  {
    if(carriedLeaves != null && carriedLeaves < 0)
    {
      return false;
    }
    else if(ChronoUnit.DAYS.between(startDate, endDate)+1 < carriedLeaves){
      return false;
    }
    return true;
  }

  @AssertTrue(message =
      ErrorMessageConstants.LEAVE_ADDITIONAL_LEAVES_ARE_MORE_THAN_ALLOCATED_TIME_PERIOD)
  private boolean isAdditionalLeavesCountValid()
  {
    if(additionalLeaves != null && additionalLeaves < 0)
    {
      return false;
    }
    else if(ChronoUnit.DAYS.between(startDate, endDate)+1 < additionalLeaves){
      return false;
    }
    return true;
  }
}