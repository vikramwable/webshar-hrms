package org.webshar.hrms.response;

import java.util.List;

public class BatchResponse
{
  private String status;
  private String message;
  private List<Object> entities;
  private int totalEntries;

  public String getStatus()
  {
    return status;
  }

  public String getMessage()
  {
    return message;
  }

  public void setStatus(final String status)
  {
    this.status = status;
  }

  public void setMessage(final String message)
  {
    this.message = message;
  }

  public List<Object> getEntities()
  {
    return entities;
  }

  public void setEntities(final List<Object> entities)
  {
    this.entities = entities;
  }

  public int getTotalEntries()
  {
    return totalEntries;
  }

  public void setTotalEntries(int totalEntries)
  {
    this.totalEntries = totalEntries;
  }
}
