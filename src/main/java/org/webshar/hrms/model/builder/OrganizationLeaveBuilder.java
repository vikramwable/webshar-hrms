package org.webshar.hrms.model.builder;

import org.springframework.stereotype.Component;
import org.webshar.hrms.model.db.LeaveType;
import org.webshar.hrms.model.db.Organization;
import org.webshar.hrms.model.db.OrganizationLeave;
import org.webshar.hrms.request.organizationleave.OrganizationLeaveCreateRequest;
import org.webshar.hrms.request.organizationleave.OrganizationLeaveUpdateRequest;

import java.util.UUID;

@Component
public class OrganizationLeaveBuilder
{

  public OrganizationLeave buildFromRequest(
      OrganizationLeaveCreateRequest organizationLeaveCreateRequest, LeaveType leaveType, Organization organization)
  {
    OrganizationLeave organizationLeave = new OrganizationLeave();
    organizationLeave.setGuid(UUID.randomUUID());
    organizationLeave.setLeaveType(leaveType);
    organizationLeave.setOrganization(organization);
    organizationLeave.setCount(organizationLeaveCreateRequest.getCount());
    return organizationLeave;
  }

  public OrganizationLeave buildFromRequest(OrganizationLeave organizationLeaveToBeUpdated,
      OrganizationLeaveUpdateRequest organizationLeaveUpdateRequest,LeaveType leaveType, Organization organization)
  {
    OrganizationLeave organizationLeaveAfterUpdate = new OrganizationLeave(
        organizationLeaveToBeUpdated);

    if (organizationLeaveUpdateRequest.getCount() != null)
    {
      organizationLeaveAfterUpdate.setCount(organizationLeaveUpdateRequest.getCount());
    }
    if(organization != null){
      organizationLeaveAfterUpdate
          .setOrganization(organization);
    }
    if(leaveType!=null){
      organizationLeaveAfterUpdate.setLeaveType(leaveType);
    }
    return organizationLeaveAfterUpdate;
  }
}