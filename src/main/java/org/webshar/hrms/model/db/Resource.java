package org.webshar.hrms.model.db;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Table(name = "resource", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Resource implements Serializable
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true, length = 11)
  private Long id;

  @Column(name = "guid", nullable = false, unique = true, length = 11)
  private UUID guid;

  @Column(name = "name", nullable = false,  unique = true,length = 200)
  private String name;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false, updatable = false)
  private Date createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at", nullable = false)
  private Date updatedAt;

  @OneToMany
  @Getter
  @Setter
  private List<Permission> permissions;

  public Long getId()
  {
    return id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public UUID getGuid()
  {
    return guid;
  }

  public void setGuid(UUID guid)
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
    return "Role {" +
        "id='" + id + '\'' +
        ", guid='" + guid + '\'' +
        ", name='" + name + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o)
    {
      return true;
    }
    if (o == null || getClass() != o.getClass())
    {
      return false;
    }
    Resource resource = (Resource) o;
    return name.equals(resource.name);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(name);
  }
}
