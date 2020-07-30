package org.webshar.hrms.request.organizationleave;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.webshar.hrms.constants.ErrorMessageConstants;

@Data
public class OrganizationLeaveCreateRequest {

  @NotNull(message = ErrorMessageConstants.ORGANIZATION_LEAVE_LEAVE_TYPE_ID_NOT_NULL)
  private Long leaveTypeId;

  @NotNull(message = ErrorMessageConstants.ORGANIZATION_LEAVE_ORGANIZATION_ID_NOT_NULL)
  private Long organizationId;

  @NotNull(message = ErrorMessageConstants.ORGANIZATION_LEAVE_COUNT_NOT_NULL)
  private Long count;

  @AssertTrue(message = ErrorMessageConstants.ORGANIZATION_LEAVE_COUNT_CAN_NOT_BE_LESS_THAN_ONE)
  private boolean isValidCount() {
    return count > 0;
  }

}
