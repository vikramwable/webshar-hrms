package org.webshar.hrms.service.exception;

public class InsufficientLeaveException extends ServiceException
{

  public InsufficientLeaveException(final String errorMessage, final Throwable errorObject)
  {
    super(errorMessage, errorObject);
  }

  public InsufficientLeaveException(final String errorMessage)
  {
    super(errorMessage);
  }

  private static final long id = 1L;
}
