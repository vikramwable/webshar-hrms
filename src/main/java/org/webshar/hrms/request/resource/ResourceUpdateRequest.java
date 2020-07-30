package org.webshar.hrms.request.resource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.webshar.hrms.constants.ErrorMessageConstants;

@Getter
@Setter
@ToString(callSuper = true)
public class ResourceUpdateRequest {

  @NotNull(message = ErrorMessageConstants.RESOURCE_NAME_NOT_NULL)
  @NotBlank(message = ErrorMessageConstants.RESOURCE_NAME_NOT_BLANK)
  private String name;

  private String[] permissions;
}
