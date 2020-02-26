package org.webshar.hrms.request.organizationleave;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.webshar.hrms.constants.ErrorMessageConstants;

public class OrganizationLeaveCreateRequest
{

  @Getter
  @Setter
  @NotNull(message = ErrorMessageConstants.ORGANIZATION_LEAVE_LEAVE_TYPE_ID_NOT_NULL)
  private Long leaveTypeId;

  @Getter
  @Setter
  @NotNull(message = ErrorMessageConstants.ORGANIZATION_LEAVE_ORGANIZATION_ID_NOT_NULL)
  private Long organizationId;

  @Getter
  @Setter
  @NotNull(message = ErrorMessageConstants.ORGANIZATION_LEAVE_COUNT_NOT_NULL)
  private Long count;

  @AssertTrue(message = ErrorMessageConstants.ORGANIZATION_LEAVE_COUNT_CAN_NOT_BE_LESS_THAN_ONE)
  private boolean isValidCount()
  {
    if (count <= 0)
    {
      return false;
    }
    else
    {
      return true;
    }
  }

}
