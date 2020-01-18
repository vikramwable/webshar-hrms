package org.webshar.hrms.request.resourcepermission;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.webshar.hrms.constants.ErrorMessageConstants;

public class ResourcePermissionCreateRequest
{

  @Getter
  @Setter
  @NotNull(message = ErrorMessageConstants.RESOURCE_PERMISSION_RESOURCE_ID_NOT_NULL)
  private Long resourceId;

  @Getter
  @Setter
  @NotNull(message = ErrorMessageConstants.RESOURCE_PERMISSION_PERMISSION_ID_NOT_NULL)
  private Long permissionId;
}
