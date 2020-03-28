package org.webshar.hrms.request.employee;

import java.util.Date;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.webshar.hrms.constants.ErrorMessageConstants;

public class EmployeeUpdateRequest
{

  @NotNull(message = ErrorMessageConstants.EMPLOYEE_ID_NOT_NULL)
  @Getter
  private Long id;

  @Getter
  @Setter
  private String empId;

  @Getter
  @Setter
  private Long organizationId;

  @Getter
  @Setter
  private String firstName;

  @Getter
  @Setter
  private String middleName;

  @Getter
  @Setter
  private String lastName;

  @Getter
  @Setter
  private Date dateOfBirth;

  @Getter
  @Setter
  private Boolean isActive;

  @Getter
  @Setter
  @Email(message = ErrorMessageConstants.EMPLOYEE_EMAIL_ID_NOT_PROPER)
  private String email;

  @Getter
  @Setter
  private Date joiningDate;

  @Getter
  @Setter
  private Date exitDate;

  @Getter
  @Setter
  private String address;

  @Getter
  @Setter
  private String contact;

  @Getter
  @Setter
  private String designation;

  @AssertTrue(message = ErrorMessageConstants.EMPLOYEE_EMP_ID_NOT_NULL)
  private boolean isValidEmployeeId()
  {
    return empId == null;
  }

  @AssertTrue(message = ErrorMessageConstants.EMPLOYEE_ORGANIZATION_ID_NOT_NULL)
  private boolean isValidOrganizationId()
  {
    return organizationId == null;
  }

  @AssertTrue(message = ErrorMessageConstants.EMPLOYEE_FIRST_NAME_NOT_BLANK)
  private boolean isValidFirstName()
  {
    return firstName == null || StringUtils.isNoneBlank(firstName);
  }

  @AssertTrue(message = ErrorMessageConstants.EMPLOYEE_LAST_NAME_NOT_BLANK)
  private boolean isValidLastName()
  {
    return lastName == null || StringUtils.isNoneBlank(lastName);
  }

  @AssertTrue(message = ErrorMessageConstants.EMPLOYEE_MIDDLE_NAME_NOT_BLANK)
  private boolean isValidMiddleName()
  {
    return middleName == null || StringUtils.isNoneBlank(middleName);
  }

  @AssertTrue(message = ErrorMessageConstants.EMPLOYEE_DATE_OF_BIRTH_NOT_NULL)
  private boolean isValidDateOfBirth()
  {
    return dateOfBirth == null;//|| StringUtils.isNoneBlank(dateOfBirth.toString());
  }

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

  @AssertTrue(message = ErrorMessageConstants.EMPLOYEE_DATE_OF_JOINING_NOT_NULL)
  private boolean isValidDateOfJoining()
  {
    return joiningDate == null;//|| StringUtils.isNoneBlank(dateOfBirth.toString());
  }

  @AssertTrue(message = ErrorMessageConstants.EMPLOYEE_DATE_OF_EXIT_NOT_NULL)
  private boolean isValidDateOfExit()
  {
    return exitDate == null;//|| StringUtils.isNoneBlank(dateOfBirth.toString());
  }

  @AssertTrue(message = ErrorMessageConstants.EMPLOYEE_ADDRESS_NOT_NULL)
  private boolean isValidAddress()
  {
    return address == null || StringUtils.isNoneBlank(middleName);
  }

  @AssertTrue(message = ErrorMessageConstants.EMPLOYEE_CONTACT_NOT_NULL)
  private boolean isValidContact()
  {
    return contact == null || StringUtils.isNoneBlank(middleName);
  }

  @AssertTrue(message = ErrorMessageConstants.EMPLOYEE_DESIGNATION_NOT_NULL)
  private boolean isValidDesignation()
  {
    return designation == null || StringUtils.isNoneBlank(middleName);
  }
}
