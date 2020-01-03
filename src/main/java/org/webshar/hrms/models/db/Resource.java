package org.webshar.hrms.models.db;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Table(name="resource", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Resource
{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true, length = 11)
  private Long id;

  @Column(name = "guid", nullable = false, unique = true, length = 11)
  private String guid;

  @Column(name = "name", nullable = false, length = 200)
  private String name;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false, updatable = false)
  private Date createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at", nullable = false)
  private Date updatedAt;

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public String getGuid()
  {
    return guid;
  }

  public void setGuid(String guid)
  {
    this.guid = guid;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public Date getCreatedAt()
  {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt)
  {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt()
  {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt)
  {
    this.updatedAt = updatedAt;
  }

  @Override
  public String toString()
  {
    return "Role {"+
        "id='" + id + '\'' +
        ", guid='" + guid + '\'' +
        ", name='" + name + '\'' +
        '}';
  }

  @Override
  public int hashCode(){

    final int prime = 31;
    int result = 1;

    result = prime * result + ((guid == null) ? 0 : guid.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
    result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());

    return result;
  }

  @Override
  public boolean equals(final Object obj){

    if (this == obj)
    {
      return true;
    }
    if (obj == null)
    {
      return false;
    }
    if (getClass() != obj.getClass())
    {
      return false;
    }
    final Resource other = (Resource) obj;

    if (guid == null)
    {
      if (other.guid != null)
      {
        return false;
      }
    }
    else if (!guid.equals(other.guid))
    {
      return false;
    }

    if (name == null)
    {
      if (other.name != null)
      {
        return false;
      }
    }
    else if (!name.equals(other.name))
    {
      return false;
    }

    if (createdAt == null)
    {
      if (other.createdAt != null)
      {
        return false;
      }
    }
    else if (!createdAt.equals(other.createdAt))
    {
      return false;
    }

    if (updatedAt == null)
    {
      if (other.updatedAt != null)
      {
        return false;
      }
    }
    else if (!updatedAt.equals(other.updatedAt))
    {
      return false;
    }
    return true;
  }
}
