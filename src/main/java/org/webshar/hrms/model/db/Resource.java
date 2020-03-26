package org.webshar.hrms.model.db;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "resource")
@Getter
@Setter
@ToString(callSuper = true)
public class Resource extends BaseModel
{
  @Column(name = "name", nullable = false,  unique = true,length = 200)
  private String name;

  @OneToMany
  private List<Permission> permissions;

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
