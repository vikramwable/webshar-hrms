package org.webshar.hrms.request.resourcepermission;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.webshar.hrms.constants.ErrorMessageConstants;

public class ResourcePermissionUpdateRequest
{

  @NotNull(message = ErrorMessageConstants.RESOURCE_PERMISSION_ID_NOT_NULL)
  @Getter
  private Long id;

  @Getter
  @Setter
  private Long resourceId;

  @Getter
  @Setter
  private Long permissionId;
}
