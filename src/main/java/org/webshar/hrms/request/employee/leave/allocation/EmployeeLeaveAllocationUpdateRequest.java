package org.webshar.hrms.request.employee.leave.allocation;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.webshar.hrms.constants.ErrorMessageConstants;

@Data
public class EmployeeLeaveAllocationUpdateRequest
{

  @NotNull(message = ErrorMessageConstants.EMPLOYEE_EMP_ID_NOT_NULL)
  private Long employeeId;

  @NotNull(message = ErrorMessageConstants.LEAVE_LEAVE_TYPE_ID_NOT_NULL)
  private Long leaveTypeId;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date startDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date endDate;

  private Long allottedLeaves;

  private Long carriedLeaves;

  private Long additionalLeaves;

  @AssertTrue(message =
      ErrorMessageConstants.EMPLOYEE_LEAVE_ALLOCATED_START_AND_END_DATE_ARE_REQUIRED)
  private boolean isDatesAreValid()
  {
    if ((endDate == null && startDate != null) || (endDate != null && startDate == null))
    {
      return false;
    }
    return true;
  }

  @AssertTrue(message = ErrorMessageConstants.LEAVE_END_DATE_CANNOT_LESS_THAN_START_DATE)
  private boolean isEndDateIsValid()
  {
    if (endDate == null)
    {
      return false;
    }
    else if (endDate.before(startDate))
    {
      return false;
    }
    return true;
  }

  @AssertTrue(message = ErrorMessageConstants.EMPLOYEE_LEAVE_ALLOCATED_LEAVES_CAN_NOT_BE_NEGATIVE)
  private boolean isAllottedLeavesValid()
  {
    if (allottedLeaves == null)
    {
      return false;
    }
    else if (allottedLeaves < 0)
    {
      return false;
    }
    else
    {
      return true;
    }
  }

  @AssertTrue(message = ErrorMessageConstants.EMPLOYEE_LEAVE_ADDITIONAL_LEAVES_CAN_NOT_BE_NEGATIVE)
  private boolean isAdditionalLeavesValid()
  {
    if (additionalLeaves == null)
    {
      return false;
    }
    else if (additionalLeaves < 0)
    {
      return false;
    }
    else
    {
      return true;
    }
  }

  @AssertTrue(message = ErrorMessageConstants.EMPLOYEE_LEAVE_CARRIED_LEAVES_CAN_NOT_BE_NEGATIVE)
  private boolean isCarriedLeavesValid()
  {
    if (carriedLeaves == null)
    {
      return false;
    }
    else if (carriedLeaves < 0)
    {
      return false;
    }
    else
    {
      return true;
    }
  }
}
