package org.webshar.hrms.request.employee.leave.allocation;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.webshar.hrms.constants.ErrorMessageConstants;

public class EmployeeLeaveAllocationUpdateRequest
{

  @Getter
  @Setter
  @NotNull(message = ErrorMessageConstants.EMPLOYEE_LEAVE_ALLOCATED_ID_CAN_NOT_BE_NUll)
  private Long id;

  @Getter
  @Setter
  @NotNull(message = ErrorMessageConstants.EMPLOYEE_EMP_ID_NOT_NULL)
  private Long employeeId;

  @Getter
  @Setter
  @NotNull(message = ErrorMessageConstants.LEAVE_LEAVE_TYPE_ID_NOT_NULL)
  private Long leaveTypeId;

  @Getter
  @Setter
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate startDate;

  @Getter
  @Setter
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate endDate;

  @Getter
  @Setter
  private Long allottedLeaves;

  @Getter
  @Setter
  private Long carriedLeaves;

  @Getter
  @Setter
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
