package org.webshar.hrms.request.roles;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotNull.List;
import lombok.Getter;
import lombok.Setter;
import org.webshar.hrms.constants.ErrorMessageConstants;

public class RoleCreateRequest
{
  @NotNull(message = ErrorMessageConstants.ROLE_NAME_NOT_NULL)
  @NotBlank(message = ErrorMessageConstants.ROLE_NAME_NOT_BLANK)
  @Getter @Setter
  private String name;

  @Getter
  @Setter
  private String [] permissions;
}
