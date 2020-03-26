package org.webshar.hrms.request.employee.leave.application;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.webshar.hrms.constants.ErrorMessageConstants;

@Getter
@Setter
@ToString
public class EmployeeLeaveApplicationUpdateRequest {

  @NotNull(message = ErrorMessageConstants.LEAVE_EMPLOYEE_ID_NOT_NULL)
  private Long employeeId;

  @NotNull(message = ErrorMessageConstants.LEAVE_LEAVE_STATUS_NOT_NULL)
  private Long leaveStatusId;
}
