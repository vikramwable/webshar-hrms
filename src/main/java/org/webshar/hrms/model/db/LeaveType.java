package org.webshar.hrms.model.db;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "leave_type")
@Getter
@Setter
@ToString(callSuper = true)
public class LeaveType extends BaseModel {

  @Column(name = "type", nullable = false, unique = true, length = 200)
  private String type;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LeaveType leaveType = (LeaveType) o;
    return type.equals(leaveType.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type);
  }
}
