package org.webshar.hrms.service.exception;

public class EntityNotFoundException extends ServiceException
{

  public EntityNotFoundException(final String errorMessage, final Throwable errorObject)
  {
    super(errorMessage, errorObject);
  }

  public EntityNotFoundException(final String errorMessage)
  {
    super(errorMessage);
  }

  private static final long id = 1L;
}
