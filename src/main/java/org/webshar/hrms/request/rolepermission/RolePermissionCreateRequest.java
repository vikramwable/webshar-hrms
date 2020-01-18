package org.webshar.hrms.request.rolepermission;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.webshar.hrms.constants.ErrorMessageConstants;

public class RolePermissionCreateRequest
{

  @Getter
  @Setter
  @NotNull(message = ErrorMessageConstants.ROLE_PERMISSION_ROLE_ID_NOT_NULL)
  private Long roleId;

  @Getter
  @Setter
  @NotNull(message = ErrorMessageConstants.ROLE_PERMISSION_PERMISSION_ID_NOT_NULL)
  private Long permissionId;
}
