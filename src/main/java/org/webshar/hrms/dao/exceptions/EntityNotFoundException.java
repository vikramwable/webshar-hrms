package org.webshar.hrms.dao.exceptions;

public class EntityNotFoundException extends DaoException
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
