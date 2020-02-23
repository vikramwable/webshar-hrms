package org.webshar.hrms.request.employee.leave.application;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.webshar.hrms.constants.ErrorMessageConstants;

public class EmployeeLeaveApplicationCreateRequest
{

  @Getter
  @Setter
  @NotNull(message = ErrorMessageConstants.LEAVE_EMPLOYEE_ID_NOT_NULL)
  private Long employeeId;

  @Getter
  @Setter
  @NotNull(message = ErrorMessageConstants.LEAVE_LEAVE_TYPE_ID_NOT_NULL)
  private Long leaveTypeId;

  @Getter
  @Setter
  @NotNull(message = ErrorMessageConstants.LEAVE_START_DATE_NOT_NULL)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date startDate;

  @Getter
  @Setter
  @NotNull(message = ErrorMessageConstants.LEAVE_END_DATE_NOT_NULL)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date endDate;

  @AssertTrue(message = ErrorMessageConstants.LEAVE_END_DATE_CANNOT_LESS_THAN_START_DATE)
  private boolean isValidEndDate()
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
}
