package org.webshar.hrms.model.db;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "permission")
@Getter
@Setter
@ToString(callSuper = true)
public class Permission extends BaseModel {

  @Column(name = "name", nullable = false, unique = true, length = 200)
  private String name;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Permission resource = (Permission) o;
    return name.equals(resource.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
