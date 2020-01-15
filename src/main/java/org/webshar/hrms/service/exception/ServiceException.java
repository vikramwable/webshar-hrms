package org.webshar.hrms.service.exception;

public class ServiceException extends Exception
{

  public ServiceException(final String errorMessage)
  {
    super(errorMessage);
  }

  public ServiceException(final String errorMessage, final Throwable errorObject)
  {
    super(errorMessage, errorObject);
  }

  private static final long id = 1L;
}

