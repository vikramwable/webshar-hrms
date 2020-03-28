package org.webshar.hrms.model.db;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name = "organization")
public class Organization extends BaseModel
{
  @Column(name = "name", nullable = false, unique = true, length = 200)
  @Getter
  @Setter
  private String name;

  @Column(name = "is_active", nullable = false)
  @Getter
  @Setter
  private Boolean isActive;

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
    this.setCreatedBy(organization.getCreatedBy());
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
