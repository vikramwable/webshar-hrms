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
}
