package org.webshar.hrms.request.employee;

import java.util.Date;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.webshar.hrms.constants.ErrorMessageConstants;

public class EmployeeCreateRequest
{

  @Getter
  @Setter
  @NotNull(message = ErrorMessageConstants.EMPLOYEE_EMP_ID_NOT_NULL)
  private String empId;

  @Getter
  @Setter
  @NotNull(message = ErrorMessageConstants.EMPLOYEE_ORGANIZATION_ID_NOT_NULL)
  private Long organizationId;

  @Getter
  @Setter
  @NotNull(message = ErrorMessageConstants.EMPLOYEE_FIRST_NAME_NOT_NULL)
  @NotBlank(message = ErrorMessageConstants.EMPLOYEE_FIRST_NAME_NOT_BLANK)
  private String firstName;

  @Getter
  @Setter
  private String middleName;

  @Getter
  @Setter
  @NotNull(message = ErrorMessageConstants.EMPLOYEE_LAST_NAME_NOT_NULL)
  @NotBlank(message = ErrorMessageConstants.EMPLOYEE_LAST_NAME_NOT_BLANK)
  private String lastName;

  @Getter
  @Setter
  @NotNull(message = ErrorMessageConstants.EMPLOYEE_DATE_OF_BIRTH_NOT_NULL)
  private Date dateOfBirth;

  @Getter
  @Setter
  private Boolean isActive;

  @Getter
  @Setter
  @Email(message = ErrorMessageConstants.EMPLOYEE_EMAIL_ID_NOT_PROPER)
  @NotNull(message = ErrorMessageConstants.EMPLOYEE_EMAIL_NOT_NULL)
  @NotBlank(message = ErrorMessageConstants.EMPLOYEE_EMAIL_NOT_BLANK)
  private String email;

  @Getter
  @Setter
  @NotNull(message = ErrorMessageConstants.EMPLOYEE_DATE_OF_JOINING_NOT_NULL)
  private Date joiningDate;

  @Getter
  @Setter
  private Date exitDate;

  @Getter
  @Setter
  private String address;

  @Getter
  @Setter
  @NotNull(message = ErrorMessageConstants.EMPLOYEE_CONTACT_NOT_NULL)
  @NotBlank(message = ErrorMessageConstants.EMPLOYEE_CONTACT_NOT_BLANK)
  private String contact;

  @Getter
  @Setter
  @NotNull(message = ErrorMessageConstants.EMPLOYEE_DESIGNATION_NOT_NULL)
  @NotBlank(message = ErrorMessageConstants.EMPLOYEE_DESIGNATION__NOT_BLANK)
  private String designation;

  @AssertTrue(message = ErrorMessageConstants.EMPLOYEE_INVALID_STATAUS)
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
