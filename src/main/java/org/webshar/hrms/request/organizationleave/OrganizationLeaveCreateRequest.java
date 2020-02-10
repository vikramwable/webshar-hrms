package org.webshar.hrms.request.organizationleave;

import javax.persistence.Column;
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

}
