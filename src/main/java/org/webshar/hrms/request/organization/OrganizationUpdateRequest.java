package org.webshar.hrms.request.organization;

import javax.validation.constraints.AssertTrue;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.webshar.hrms.constants.ErrorMessageConstants;

@Data
public class OrganizationUpdateRequest {

  private String name;

  private Boolean isActive;

  @AssertTrue(message = ErrorMessageConstants.ORGANIZATION_NAME_NOT_BLANK)
  private boolean isValidName() {
    return name == null || StringUtils.isNoneBlank(name);
  }
}
