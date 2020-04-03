package org.webshar.hrms.constants;

public class ErrorMessageConstants {

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
   * Error messages related to Permission entity
   */
  public static final String PERMISSION_BY_ID_NOT_FOUND = "Permission with given id not found";
  public static final String PERMISSION_WITH_GIVEN_NAME_NOT_FOUND = "Permission with given name not found";

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
  public static final String EMPLOYEE_MIDDLE_NAME_NOT_BLANK = "Employee middle name cannot be "
      + "blank";
  public static final String EMPLOYEE_DATE_OF_BIRTH_NOT_BLANK = "Employee date of birth cannot be"
      + " blank";
  public static final String EMPLOYEE_DATE_OF_BIRTH_NOT_NULL = "Employee date of birth cannot be "
      + "null";
  public static final String EMPLOYEE_EMAIL_NOT_NULL = "Employee email cannot be null";
  public static final String EMPLOYEE_EMAIL_NOT_BLANK = "Employee email cannot be blank";
  public static final String EMPLOYEE_EMAIL_ID_NOT_PROPER = "Please enter a valid Email ID";
  public static final String EMPLOYEE_DATE_OF_JOINING_NOT_NULL = "Employee date of joining cannot"
      + " be null";
  public static final String EMPLOYEE_DATE_OF_JOINING_NOT_BLANK = "Employee date of joining "
      + "cannot be blank";
  public static final String EMPLOYEE_DATE_OF_EXIT_NOT_NULL = "Employee date of exit cannot be "
      + "null";
  public static final String EMPLOYEE_CONTACT_NOT_NULL = "Employee contact cannot be null";
  public static final String EMPLOYEE_CONTACT_NOT_BLANK = "Employee contact cannot be blank";
  public static final String EMPLOYEE_ADDRESS_NOT_NULL = "Employee address cannot be null";
  public static final String EMPLOYEE_DESIGNATION_NOT_NULL = "Employee designation cannot be null";
  public static final String EMPLOYEE_DESIGNATION_NOT_BLANK = "Employee designation cannot be blank";
  public static final String EMPLOYEE_ID_NOT_NULL = "Employee id cannot be null";
  public static final String EMPLOYEE_EMP_ID_NOT_NULL = "Employee emp_id cannot be null";
  public static final String EMPLOYEE_ORGANIZATION_ID_NOT_NULL = "Employee organization id cannot be null";
  public static final String EMPLOYEE_DUPLICATE_EMAIL = "Employee with given email id or empId already "
      + "exists";
  public static final String EMPLOYEE_BY_ID_NOT_FOUND = "Employee with given id not found";
  public static final String EMPLOYEE_INVALID_STATAUS = "Employee isActive value is "
      + "should be true or false";
  /**
   * Employee search related constants
   */
  public static final String EMPLOYEE_INVALID_ORDER_BY_VALUE = "Invalid order by value.";
  public static final String EMPLOYEE_INVALID_INVALID_SORT_BY_COLUMN = "Invalid sort by value.";
  public static final String INVALID_PER_PAGE_VALUE = "Invalid per page value.";
  public static final String INVALID_PAGE_PARAMETER_ERROR = "Invalid page value.";
  /**
   * Error messages related to Role_Permission entity
   */
  public static final String ROLE_PERMISSION_ID_NOT_NULL = "RolePermission id cannot be null";
  public static final String ROLE_PERMISSION_ROLE_ID_NOT_NULL = "Role id cannot be null";
  public static final String ROLE_PERMISSION_PERMISSION_ID_NOT_NULL = "Permission id cannot be "
      + "null";
  public static final String ROLE_PERMISSION_BY_ID_NOT_FOUND = "RolePermission with given id not "
      + "found";

  /**
   * Error messages related to Resource_Permission entity
   */
  public static final String RESOURCE_PERMISSION_ID_NOT_NULL = "ResourcePermission id cannot be "
      + "null";
  public static final String RESOURCE_PERMISSION_RESOURCE_ID_NOT_NULL = "Resource id cannot be "
      + "null";
  public static final String RESOURCE_PERMISSION_PERMISSION_ID_NOT_NULL = "Permission id cannot "
      + "be null";
  public static final String RESOURCE_PERMISSION_BY_ID_NOT_FOUND = "ResourcePermission with given"
      + " id not found";
  /**
   * Error messages related to Organization_Leave entity
   */

  public static final String ORGANIZATION_LEAVE_LEAVE_TYPE_ID_NOT_NULL = "LeaveTypeId cannot be "
      + "null";
  public static final String ORGANIZATION_LEAVE_ORGANIZATION_ID_NOT_NULL = "OrganizationId cannot"
      + " be null";
  public static final String ORGANIZATION_LEAVE_COUNT_NOT_NULL = "Leave count cannot be null";
  public static final String ORGANIZATION_LEAVE_COUNT_CAN_NOT_BE_LESS_THAN_ONE = "Leave count "
      + "must be greater than zero";
  public static final String ORGANIZATION_LEAVE_WITH_ORGANIZATION_AND_LEAVE_ID_EXISTS =
      "OrganizationLeave record with given organizationId and leaveTypeId is already exits";
  public static final String ORGANIZATION_LEAVE_BY_ID_NOT_NULL = "OrganizationLeave with given Id"
      + " not found";
  /**
   * Error messages related to Leave_Type entity
   */
  public static final String LEAVE_TYPE_BY_ID_NOT_FOUND = "LeaveType with given id not found";
  /**
   * Error messages related to Leave entity
   */
  public static final String LEAVE_BY_ID_NOT_FOUND = "LeaveApplication with given id not found";
  public static final String LEAVE_EMPLOYEE_ID_NOT_NULL = "EmployeeId cannot be null";
  public static final String LEAVE_LEAVE_TYPE_ID_NOT_NULL = "LeaveTypeId cannot be null";
  public static final String LEAVE_LEAVE_STATUS_NOT_NULL = "LeaveStatusId value cannot be null";
  public static final String LEAVE_START_DATE_NOT_NULL = "StartDate cannot be null";
  public static final String LEAVE_END_DATE_NOT_NULL = "EndDate cannot be null";
  public static final String LEAVE_START_DATE_CANNOT_LESS_THAN_TODAY = "StartDate can not be less"
      + " than today";
  public static final String LEAVE_END_DATE_CANNOT_LESS_THAN_START_DATE = "EndDate can not be "
      + "less than StartDate";
  public static final String LEAVE_WITH_GIVEN_TYPE_AND_STATUS_EXISTS = "Leave with given start "
      + "and end date already exists for the provided employee Id";
  public static final String LEAVE_INSUFFICIENT_LEAVE_COUNT_OF_GIVEN_LEAVE_TYPE = "Insufficient "
      + "leaves are available of given type of leave";
  public static final String LEAVE_NOT_ALLOCATED_FOR_GIVEN_DATE_RANGE = "Leaves are not allocated"
      + " for a applied date range";
  public static final String LEAVE_APPLICATION_WITH_GIVEN_LEAVE_TYPE_AND_START_DATE_AND_END_DATE_OVERLAPPING = "Employee has already applied leave with given start and end date overlapping with existing records";
  public static final String LEAVE_ALLOCATED_LEAVES_ARE_MORE_THAN_ALLOCATED_TIME_PERIOD =
      "Allotted leaves count is must be greater than or equal to zero and must be more than the selected date range";
  public static final String LEAVE_CARRIED_LEAVES_ARE_MORE_THAN_ALLOCATED_TIME_PERIOD = "Carried "
      + "leaves count is must be greater than or equal to zero and must be more than the selected date range";
  public static final String LEAVE_ADDITIONAL_LEAVES_ARE_MORE_THAN_ALLOCATED_TIME_PERIOD =
      "Additional leaves count is must be greater than or equal to zero and must be more than the selected date range";
  /**
   * Error messages related to Leave_Status entity
   */
  public static final String LEAVE_STATUS_BY_ID_NOT_FOUND = "LeaveStatus with given id not found";
  public static final String LEAVE_STATUS_BY_STATUS_NAME_NOT_FOUND = "LeaveStatus with given "
      + "status value not found";

  /**
   * Error messages related to employee_leave_allocation entity
   */
  public static final String EMPLOYEE_LEAVE_ALLOCATED_BY_ID_NOT_FOUND = "Employee allocated leave"
      + " with given id not found ";
  public static final String EMPLOYEE_LEAVE_ALLOCATED_ID_CAN_NOT_BE_NUll = "Employee allocated "
      + "leave id can not be null ";
  public static final String EMPLOYEE_LEAVE_ALLOTTED_LEAVES_NOT_NULL = "Allotted leaves value can"
      + " not be null";
  public static final String EMPLOYEE_LEAVE_CARRIED_LEAVES_NOT_NULL = "Carried leaves value can "
      + "not be null";
  public static final String EMPLOYEE_LEAVE_ADDITIONAL_LEAVES_NOT_NULL = "Additional leaves value"
      + " can not be null";
  public static final String EMPLOYEE_LEAVE_TOTAL_LEAVES_NOT_NULL = "Total leaves value can not "
      + "be null";
  public static final String EMPLOYEE_LEAVE_BY_ID_NOT_FOUND = "Employee leave allocation with "
      + "given id not found";
  public static final String EMPLOYEE_LEAVE_ALLOCATED_WITH_GIVEN_LEAVE_TYPE_AND_START_DATE_AND_END_DATE_OVERLAPPING = "Employee has already allocated leave with given start and end date overlapping with existing records";
  public static final String EMPLOYEE_LEAVE_ALLOCATED_START_AND_END_DATE_ARE_REQUIRED = "Start "
      + "and end dates are required";
  public static final String EMPLOYEE_LEAVE_ALLOCATED_LEAVES_CAN_NOT_BE_NEGATIVE = "Allotted "
      + "leaves can not be less than zero";
  public static final String EMPLOYEE_LEAVE_ADDITIONAL_LEAVES_CAN_NOT_BE_NEGATIVE = "Additional "
      + "leaves can not be less than zero";
  public static final String EMPLOYEE_LEAVE_APPLIED_LEAVES_CAN_NOT_BE_NEGATIVE = "Applied leaves "
      + "can not be less than zero";
  public static final String EMPLOYEE_LEAVE_TOTAL_LEAVES_CAN_NOT_BE_NEGATIVE = "Applied leaves "
      + "can not be less than zero";
  public static final String EMPLOYEE_LEAVE_CARRIED_LEAVES_CAN_NOT_BE_NEGATIVE = "Carried leaves "
      + "can not be less than zero";
  public static final String EMPLOYEE_LEAVE_GIVEN_TYPE_OF_LEAVE_NOT_ALLOCATED_FOR_GIVEN_EMPLOYEE
      = "Given type of leave is not allocated for given employee in given date range";
}
