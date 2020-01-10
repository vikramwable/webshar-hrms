package org.webshar.hrms.service.exception;

public class EntityAlreadyExistsException extends ServiceException
{
  public EntityAlreadyExistsException(final String errorMsg, final Throwable errorObject)
  {
    super(errorMsg, errorObject);
  }

  public EntityAlreadyExistsException(final String errorMsg)
  {
    super(errorMsg);
  }

  private static final long serialVersionUID = 1L;
}
