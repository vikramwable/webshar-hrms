package org.webshar.hrms.model.builder;

import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.webshar.hrms.model.db.Organization;
import org.webshar.hrms.model.db.OrganizationLeave;
import org.webshar.hrms.request.organization.OrganizationCreateRequest;
import org.webshar.hrms.request.organization.OrganizationUpdateRequest;
import org.webshar.hrms.request.organizationleave.OrganizationLeaveCreateRequest;
import org.webshar.hrms.request.organizationleave.OrganizationLeaveUpdateRequest;

@Component
public class OrganizationLeaveBuilder
{

  public OrganizationLeave buildFromRequest(
      OrganizationLeaveCreateRequest organizationLeaveCreateRequest)
  {
    OrganizationLeave organizationLeave = new OrganizationLeave();
    organizationLeave.setLeaveTypeId(organizationLeaveCreateRequest.getLeaveTypeId());
    organizationLeave.setOrganizationId(organizationLeaveCreateRequest.getOrganizationId());
    organizationLeave.setCount(organizationLeaveCreateRequest.getCount());
    return organizationLeave;
  }

  public OrganizationLeave buildFromRequest(OrganizationLeave organizationLeaveToBeUpdated,
      OrganizationLeaveUpdateRequest organizationLeaveUpdateRequest)
  {
    OrganizationLeave organizationLeaveAfterUpdate = new OrganizationLeave(
        organizationLeaveToBeUpdated);

    if (organizationLeaveUpdateRequest.getLeaveTypeId() != null)
    {
      organizationLeaveAfterUpdate.setLeaveTypeId(organizationLeaveUpdateRequest.getLeaveTypeId());
    }
    if (organizationLeaveUpdateRequest.getOrganizationId() != null)
    {
      organizationLeaveAfterUpdate
          .setOrganizationId(organizationLeaveUpdateRequest.getOrganizationId());
    }
    if (organizationLeaveUpdateRequest.getCount() != null)
    {
      organizationLeaveAfterUpdate.setCount(organizationLeaveUpdateRequest.getCount());
    }

    return organizationLeaveAfterUpdate;
  }
}