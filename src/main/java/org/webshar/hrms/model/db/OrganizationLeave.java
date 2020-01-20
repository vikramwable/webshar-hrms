package org.webshar.hrms.model.db;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "organization_leave", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class OrganizationLeave implements Serializable
{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true, length = 11)
  @Getter
  @Setter
  private Long id;

  @Column(name = "leave_type_id", nullable = false, unique = true, length = 11)
  @Getter
  @Setter
  private Long leaveTypeId;

  @Column(name = "org_id", nullable = false, unique = true, length = 11)
  @Getter
  @Setter
  private Long organizationId;

  @Column(name = "count", nullable = false, unique = true, length = 11)
  @Getter
  @Setter
  private Long count;

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

  public OrganizationLeave()
  {

  }

  public OrganizationLeave(OrganizationLeave organizationLeave)
  {
    this.setId(organizationLeave.getId());
    this.setLeaveTypeId(organizationLeave.getLeaveTypeId());
    this.setOrganizationId(organizationLeave.getOrganizationId());
    this.setCount(organizationLeave.getCount());
    this.setCreatedAt(organizationLeave.getCreatedAt());
  }

  /**
   *
   */
  private static final long serialVersionUID = 1L;
}
