package org.webshar.hrms.response;

public class WHResponse<T>
{
  public static String SUCCESS = "Success";
  public static String FAILED = "Failed";

  private String status;
  private String message;
  private T entity;

  public String getStatus()
  {
    return status;
  }

  public WHResponse<T> setStatus(final String status)
  {
    this.status = status;
    return this;
  }

  public String getMessage()
  {
    return message;
  }

  public WHResponse<T> setMessage(final String message)
  {
    this.message = message;
    return this;
  }

  public T getEntity()
  {
    return entity;
  }

  public WHResponse<T> setEntity(final T entity)
  {
    this.entity = entity;
    return this;
  }
}
