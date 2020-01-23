package org.webshar.hrms.request.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.webshar.hrms.constants.ErrorMessageConstants;

public class ResourceUpdateRequest
{
  @NotNull(message = ErrorMessageConstants.RESOURCE_ID_NOT_NULL)
  @Getter
  private Long id;

  @NotNull(message = ErrorMessageConstants.RESOURCE_NAME_NOT_NULL)
  @NotBlank(message = ErrorMessageConstants.RESOURCE_NAME_NOT_BLANK)
  @Getter
  @Setter
  private String name;

  @Getter
  @Setter
  private String [] permissions;
}
