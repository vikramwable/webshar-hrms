package org.webshar.hrms.request.organizationleave;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.webshar.hrms.constants.ErrorMessageConstants;

public class OrganizationLeaveUpdateRequest
{

  @NotNull(message = ErrorMessageConstants.RESOURCE_PERMISSION_ID_NOT_NULL)
  @Getter
  private Long id;

  @Getter
  @Setter
  private Long leaveTypeId;

  @Getter
  @Setter
  private Long organizationId;

  @Getter
  @Setter
  private Long count;

}
