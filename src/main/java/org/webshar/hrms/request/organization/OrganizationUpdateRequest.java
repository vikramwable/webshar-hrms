package org.webshar.hrms.request.organization;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.webshar.hrms.constants.ErrorMessageConstants;

public class OrganizationUpdateRequest
{

  @NotNull(message = ErrorMessageConstants.ORGANIZATION_ID_NOT_NULL)
  @Getter
  private Long id;

  @Getter
  @Setter
  private String name;

  @Getter
  @Setter
  private Boolean isActive;

  @AssertTrue(message = ErrorMessageConstants.ORGANIZATION_NAME_NOT_BLANK)
  private boolean isValidName()
  {
    return name == null || StringUtils.isNoneBlank(name);
  }

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
