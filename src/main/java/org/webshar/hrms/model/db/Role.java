package org.webshar.hrms.model.db;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "role")
@Getter
@Setter
@ToString(callSuper = true)
public class Role extends BaseModel
{

  @Column(name = "name", nullable = false, unique = true, length = 200)
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
    Role role = (Role) o;
    return name.equals(role.name);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(name);
  }
}
