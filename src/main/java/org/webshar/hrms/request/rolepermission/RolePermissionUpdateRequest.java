package org.webshar.hrms.request.rolepermission;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.webshar.hrms.constants.ErrorMessageConstants;

public class RolePermissionUpdateRequest
{

  @NotNull(message = ErrorMessageConstants.ROLE_PERMISSION_ID_NOT_NULL)
  @Getter
  private Long id;

  @Getter
  @Setter
  private Long roleId;

  @Getter
  @Setter
  private Long permissionId;
}
