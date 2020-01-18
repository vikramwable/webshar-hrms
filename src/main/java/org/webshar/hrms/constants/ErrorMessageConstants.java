package org.webshar.hrms.constants;

public class ErrorMessageConstants
{

  /**
   * Error messages related to Role entity
   */
  public static final String ROLE_NAME_NOT_BLANK = "Role name cannot be blank";
  public static final String ROLE_NAME_NOT_NULL = "Role name cannot be null";
  public static final String ROLE_ID_NOT_NULL = "Role id cannot be null";
  public static final String ROLE_DUPLICATE_NAME = "Role with given name already exists";
  public static final String ROLE_BY_ID_NOT_FOUND = "Role with given id not found";

  /**
   * Error messages related to Resource entity
   */
  public static final String RESOURCE_NAME_NOT_BLANK = "Resource name cannot be blank";
  public static final String RESOURCE_NAME_NOT_NULL = "Resource name cannot be null";
  public static final String RESOURCE_ID_NOT_NULL = "Resource id cannot be null";
  public static final String RESOURCE_DUPLICATE_NAME = "Resource with given name already exists";
  public static final String RESOURCE_BY_ID_NOT_FOUND = "Resource with given id not found";

  /**
   * Error messages related to Organization entity
   */
  public static final String ORGANIZATION_NAME_NOT_BLANK = "Organization name cannot be blank";
  public static final String ORGANIZATION_NAME_NOT_NULL = "Organization name cannot be null";
  public static final String ORGANIZATION_ID_NOT_NULL = "Organization id cannot be null";
  public static final String ORGANIZATION_DUPLICATE_NAME = "Organization with given name already "
      + "exists";
  public static final String ORGANIZATION_BY_ID_NOT_FOUND = "Organization with given id not found";
  public static final String ORGANIZATION_INVALID_STATAUS = "Organization isActive value is "
      + "should be true or false";

  /**
   * Error messages related to Employee entity
   */
  public static final String EMPLOYEE_FIRST_NAME_NOT_BLANK = "Employee first name cannot be blank";
  public static final String EMPLOYEE_FIRST_NAME_NOT_NULL = "Employee first name cannot be null";
  public static final String EMPLOYEE_LAST_NAME_NOT_BLANK = "Employee last name cannot be blank";
  public static final String EMPLOYEE_LAST_NAME_NOT_NULL = "Employee last name cannot be null";
  public static final String EMPLOYEE_MIDDLE_NAME_NOT_BLANK = "Employee middle name cannot be blank";
  public static final String EMPLOYEE_DATE_OF_BIRTH_NOT_BLANK = "Employee date of birth cannot be blank";
  public static final String EMPLOYEE_DATE_OF_BIRTH_NOT_NULL = "Employee date of birth cannot be null";
  public static final String EMPLOYEE_EMAIL_NOT_NULL = "Employee email cannot be null";
  public static final String EMPLOYEE_EMAIL_NOT_BLANK = "Employee email cannot be blank";
  public static final String EMPLOYEE_EMAIL_ID_NOT_PROPER = "Please enter a valid Email ID";
  public static final String EMPLOYEE_DATE_OF_JOINING_NOT_NULL = "Employee date of joining cannot be null";
  public static final String EMPLOYEE_DATE_OF_JOINING_NOT_BLANK = "Employee date of joining cannot be blank";
  public static final String EMPLOYEE_DATE_OF_EXIT_NOT_NULL = "Employee date of exit cannot be null";
  public static final String EMPLOYEE_CONTACT_NOT_NULL = "Employee contact cannot be null";
  public static final String EMPLOYEE_CONTACT_NOT_BLANK = "Employee contact cannot be blank";
  public static final String EMPLOYEE_ADDRESS_NOT_NULL = "Employee address cannot be null";
  public static final String EMPLOYEE_DESIGNATION_NOT_NULL = "Employee designation cannot be null";
  public static final String EMPLOYEE_DESIGNATION__NOT_BLANK = "Employee designation cannot be blank";
  public static final String EMPLOYEE_ID_NOT_NULL = "Employee id cannot be null";

  public static final String EMPLOYEE_EMP_ID_NOT_NULL = "Employee emp_id cannot be null";
  public static final String EMPLOYEE_ORGANIZATION_ID_NOT_NULL = "Employee organization id cannot be null";

  public static final String EMPLOYEE_DUPLICATE_EMAIL = "Employee with given email already "
      + "exists";
  public static final String EMPLOYEE_BY_ID_NOT_FOUND = "Employee with given id not found";
  public static final String EMPLOYEE_INVALID_STATAUS = "Employee isActive value is "
      + "should be true or false";

  /**
   * Error messages related to Role_Permission entity
   */
  public static final String ROLE_PERMISSION_ID_NOT_NULL = "RolePermission id cannot be null";
  public static final String ROLE_PERMISSION_ROLE_ID_NOT_NULL = "Role id cannot be null";
  public static final String ROLE_PERMISSION_PERMISSION_ID_NOT_NULL = "Permission id cannot be null";
  public static final String ROLE_PERMISSION_BY_ID_NOT_FOUND = "RolePermission with given id not found";
}
