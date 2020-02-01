package org.webshar.hrms.model.db;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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

  @OneToOne
  @Getter
  @Setter
  private LeaveType leaveType;

  @OneToOne
  @Getter
  @Setter
  private Organization organization;

  public OrganizationLeave()
  {

  }

  public OrganizationLeave(OrganizationLeave organizationLeave)
  {
    this.setId(organizationLeave.getId());
    this.setLeaveType(organizationLeave.getLeaveType());
    this.setOrganization(organizationLeave.getOrganization());
    this.setCount(organizationLeave.getCount());
    this.setCreatedAt(organizationLeave.getCreatedAt());
  }

  /**
   *
   */
  private static final long serialVersionUID = 1L;
}