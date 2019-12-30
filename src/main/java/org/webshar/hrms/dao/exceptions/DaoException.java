package org.webshar.hrms.dao.exceptions;

public class DaoException extends Exception
{

  public DaoException(final String errorMessage)
  {
    super(errorMessage);
  }

  public DaoException(final String errorMessage, final Throwable errorObject)
  {
    super(errorMessage, errorObject);
  }

  private static final long id = 1L;
}

