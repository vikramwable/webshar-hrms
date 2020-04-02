package org.webshar.hrms.request.employee;

import java.util.Date;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.webshar.hrms.constants.ErrorMessageConstants;

@Data
public class EmployeeUpdateRequest {

  private String empId;

  private Long organizationId;

  private String firstName;

  private String middleName;

  private String lastName;

  private Date dateOfBirth;

  private Boolean isActive;

  @Email(message = ErrorMessageConstants.EMPLOYEE_EMAIL_ID_NOT_PROPER)
  private String email;

  private Date joiningDate;

  private Date exitDate;

  private String address;

  private String contact;

  private String designation;
  
}
