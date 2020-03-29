package org.webshar.hrms.request.employee.leave.allocation;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.webshar.hrms.constants.ErrorMessageConstants;

@Data
public class EmployeeLeaveAllocationUpdateRequest {

  @NotNull(message = ErrorMessageConstants.EMPLOYEE_EMP_ID_NOT_NULL)
  private Long employeeId;

  @NotNull(message = ErrorMessageConstants.LEAVE_LEAVE_TYPE_ID_NOT_NULL)
  private Long leaveTypeId;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate startDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate endDate;

  private Long allottedLeaves;

  private Long carriedLeaves;

  private Long additionalLeaves;

  @AssertTrue(message =
      ErrorMessageConstants.EMPLOYEE_LEAVE_ALLOCATED_START_AND_END_DATE_ARE_REQUIRED)
  private boolean isDatesAreValid() {
    return (endDate != null || startDate == null) && (endDate == null || startDate != null);
  }

  @AssertTrue(message = ErrorMessageConstants.LEAVE_END_DATE_CANNOT_LESS_THAN_START_DATE)
  private boolean isEndDateIsValid()
  {
    if (endDate != null && endDate.isBefore(startDate) )
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
    return true;
  }
}
