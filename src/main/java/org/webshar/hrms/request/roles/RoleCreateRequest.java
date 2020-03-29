package org.webshar.hrms.request.roles;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.webshar.hrms.constants.ErrorMessageConstants;

@Data
public class RoleCreateRequest {

  @NotNull(message = ErrorMessageConstants.ROLE_NAME_NOT_NULL)
  @NotBlank(message = ErrorMessageConstants.ROLE_NAME_NOT_BLANK)
  private String name;

  private String[] permissions;
}
