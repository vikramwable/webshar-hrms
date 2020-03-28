package org.webshar.hrms.request.employee.leave.allocation;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
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
  private Date startDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date endDate;

  private Long allottedLeaves;

  private Long carriedLeaves;

  private Long additionalLeaves;

  @AssertTrue(message =
      ErrorMessageConstants.EMPLOYEE_LEAVE_ALLOCATED_START_AND_END_DATE_ARE_REQUIRED)
  private boolean isDatesAreValid() {
    return (endDate != null || startDate == null) && (endDate == null || startDate != null);
  }

  @AssertTrue(message = ErrorMessageConstants.LEAVE_END_DATE_CANNOT_LESS_THAN_START_DATE)
  private boolean isEndDateIsValid() {
    return endDate != null && !endDate.before(startDate);
  }

  @AssertTrue(message = ErrorMessageConstants.EMPLOYEE_LEAVE_ALLOCATED_LEAVES_CAN_NOT_BE_NEGATIVE)
  private boolean isAllottedLeavesValid() {
    return allottedLeaves != null && allottedLeaves >= 0;
  }

  @AssertTrue(message = ErrorMessageConstants.EMPLOYEE_LEAVE_ADDITIONAL_LEAVES_CAN_NOT_BE_NEGATIVE)
  private boolean isAdditionalLeavesValid() {
    return additionalLeaves != null && additionalLeaves >= 0;
  }

  @AssertTrue(message = ErrorMessageConstants.EMPLOYEE_LEAVE_CARRIED_LEAVES_CAN_NOT_BE_NEGATIVE)
  private boolean isCarriedLeavesValid() {
    return carriedLeaves != null && carriedLeaves >= 0;
  }
}
