package org.webshar.hrms.model.db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "organization_leave")
@Getter
@Setter
@ToString(callSuper = true)
public class OrganizationLeave extends BaseModel {

  private static final long serialVersionUID = 1L;
  @Column(name = "count", nullable = false, unique = true, length = 11)
  private Long count;
  @OneToOne
  private LeaveType leaveType;
  @OneToOne
  private Organization organization;

  public OrganizationLeave() {

  }

  public OrganizationLeave(OrganizationLeave organizationLeave) {
    this.setId(organizationLeave.getId());
    this.setGuid(organizationLeave.getGuid());
    this.setLeaveType(organizationLeave.getLeaveType());
    this.setOrganization(organizationLeave.getOrganization());
    this.setCount(organizationLeave.getCount());
    this.setCreatedAt(organizationLeave.getCreatedAt());
  }
}