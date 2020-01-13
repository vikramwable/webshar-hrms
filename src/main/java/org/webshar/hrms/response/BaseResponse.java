package org.webshar.hrms.response;

public class BaseResponse<T>
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

  public BaseResponse<T> setStatus(final String status)
  {
    this.status = status;
    return this;
  }

  public String getMessage()
  {
    return message;
  }

  public BaseResponse<T> setMessage(final String message)
  {
    this.message = message;
    return this;
  }

  public T getEntity()
  {
    return entity;
  }

  public BaseResponse<T> setEntity(final T entity)
  {
    this.entity = entity;
    return this;
  }
}
