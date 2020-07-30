package org.webshar.hrms.request.organization;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.webshar.hrms.constants.ErrorMessageConstants;

@Data
public class OrganizationCreateRequest {

  @NotNull(message = ErrorMessageConstants.ORGANIZATION_NAME_NOT_NULL)
  @NotBlank(message = ErrorMessageConstants.ORGANIZATION_NAME_NOT_BLANK)
  private String name;

  private Boolean isActive;

  @AssertTrue(message = ErrorMessageConstants.ORGANIZATION_INVALID_STATAUS)
  private boolean isValidStatus() {
    return isActive == null || isActive || !isActive;
  }
}