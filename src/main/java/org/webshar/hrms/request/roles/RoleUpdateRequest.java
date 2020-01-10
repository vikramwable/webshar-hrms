package org.webshar.hrms.request.roles;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.webshar.hrms.constants.ErrorMessageConstants;

@Component
public class RoleUpdateRequest
{
  @NotNull(message = ErrorMessageConstants.ROLE_ID_NOT_NULL)
  @Getter
  private Long id;

  @NotNull(message = ErrorMessageConstants.ROLE_NAME_NOT_NULL)
  @NotBlank(message = ErrorMessageConstants.ROLE_NAME_NOT_BLANK)
  @Getter
  @Setter
  private String name;
}
