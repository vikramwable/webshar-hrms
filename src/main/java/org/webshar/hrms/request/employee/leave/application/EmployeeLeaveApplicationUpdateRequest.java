package org.webshar.hrms.request.employee.leave.application;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.webshar.hrms.constants.ErrorMessageConstants;

public class EmployeeLeaveApplicationUpdateRequest
{
  @Getter
  @Setter
  @NotNull(message = ErrorMessageConstants.LEAVE_BY_ID_NOT_FOUND)
  private Long id;

  @Getter
  @Setter
  @NotNull(message = ErrorMessageConstants.LEAVE_EMPLOYEE_ID_NOT_NULL)
  private Long employeeId;

  @Getter
  @Setter
  @NotNull(message = ErrorMessageConstants.LEAVE_LEAVE_STATUS_NOT_NULL)
  private Long leaveStatusId;
}
