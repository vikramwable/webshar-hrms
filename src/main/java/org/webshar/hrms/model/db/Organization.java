package org.webshar.hrms.model.db;

import java.io.Serializable;
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
@Table(name = "organization", uniqueConstraints = @UniqueConstraint(columnNames = {"id"}))
public class Organization implements Serializable
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

  @Column(name = "name", nullable = false, unique = true, length = 200)
  @Getter
  @Setter
  private String name;

  @Column(name = "is_active", nullable = false)
  @Getter
  @Setter
  private Boolean isActive;

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

  public Organization()
  {

  }

  public Organization(final Organization organization)
  {
    this.setId(organization.getId());
    this.setName(organization.getName());
    this.setGuid(organization.getGuid());
    this.setIsActive(organization.getIsActive());
    this.setCreatedAt(organization.getCreatedAt());
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
    Organization that = (Organization) o;
    return name.equals(that.name);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(name);
  }
}
