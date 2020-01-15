package org.webshar.hrms.request.organization;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.webshar.hrms.constants.ErrorMessageConstants;

public class OrganizationCreateRequest
{

  @NotNull(message = ErrorMessageConstants.ORGANIZATION_NAME_NOT_NULL)
  @NotBlank(message = ErrorMessageConstants.ORGANIZATION_NAME_NOT_BLANK)
  @Getter
  @Setter
  private String name;

  @Getter
  @Setter
  private Boolean isActive;

  @AssertTrue(message = ErrorMessageConstants.ORGANIZATION_INVALID_STATAUS)
  private boolean isValidStatus()
  {
    if (isActive == null)
    {
      return true;
    }
    else if (isActive != true && isActive != false)
    {
      return false;
    }
    return true;
  }
}