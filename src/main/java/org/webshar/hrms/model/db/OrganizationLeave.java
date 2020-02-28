package org.webshar.hrms.model.db;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "organization_leave")
@Getter
@Setter
public class OrganizationLeave extends BaseModel
{

  @Column(name = "count", nullable = false, unique = true, length = 11)
  private Long count;

  @OneToOne
  private LeaveType leaveType;

  @OneToOne
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