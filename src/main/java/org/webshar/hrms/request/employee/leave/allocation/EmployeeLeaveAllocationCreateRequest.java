package org.webshar.hrms.request.employee.leave.allocation;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
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
  private Date startDate;

  @NotNull(message = ErrorMessageConstants.LEAVE_END_DATE_NOT_NULL)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date endDate;

  @NotNull(message = ErrorMessageConstants.EMPLOYEE_LEAVE_ALLOTTED_LEAVES_NOT_NULL)
  private Long allottedLeaves;

  @NotNull(message = ErrorMessageConstants.EMPLOYEE_LEAVE_CARRIED_LEAVES_NOT_NULL)
  private Long carriedLeaves;

  @NotNull(message = ErrorMessageConstants.EMPLOYEE_LEAVE_ADDITIONAL_LEAVES_NOT_NULL)
  private Long additionalLeaves;

  @AssertTrue(message = ErrorMessageConstants.LEAVE_END_DATE_CANNOT_LESS_THAN_START_DATE)
  private boolean isValidEndDate() {
    return endDate != null && !endDate.before(startDate);
  }

}