package org.webshar.hrms.request.roles;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.webshar.hrms.constants.ErrorMessageConstants;

@Getter
@Setter
@ToString
public class RoleUpdateRequest {

  @NotNull(message = ErrorMessageConstants.ROLE_NAME_NOT_NULL)
  @NotBlank(message = ErrorMessageConstants.ROLE_NAME_NOT_BLANK)
  private String name;

  private String[] permissions;
}
