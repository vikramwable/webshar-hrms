package org.webshar.hrms.model.db;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "leave_status", uniqueConstraints = @UniqueConstraint(columnNames = {"id"}))
public class LeaveStatus
{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true, length = 11)
  @Getter
  @Setter
  private Long id;

  @Column(name = "guid", nullable = false, unique = true, length = 11)
  @Getter
  @Setter
  private UUID guid;

  @Column(name = "status", nullable = false, unique = true, length = 200)
  @Getter
  @Setter
  private String status;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false, updatable = false)
  @Getter
  @Setter
  private Date createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at", nullable = false)
  @Getter
  @Setter
  private Date updatedAt;

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
    LeaveStatus that = (LeaveStatus) o;
    return id.equals(that.id) &&
        guid.equals(that.guid) &&
        status.equals(that.status);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(id, guid, status);
  }
}
