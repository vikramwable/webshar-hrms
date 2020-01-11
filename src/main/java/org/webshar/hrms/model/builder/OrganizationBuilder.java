package org.webshar.hrms.model.builder;

import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.webshar.hrms.model.db.Organization;
import org.webshar.hrms.request.organization.OrganizationCreateRequest;
import org.webshar.hrms.request.organization.OrganizationUpdateRequest;

@Component
public class OrganizationBuilder
{

  public Organization buildFromRequest(OrganizationCreateRequest organizationCreateRequest)
  {
    Organization organization = new Organization();
    organization.setName(organizationCreateRequest.getName());
    organization.setGuid(UUID.randomUUID());
    organization.setIsActive(
        organizationCreateRequest.getIsActive() != null ? organizationCreateRequest.getIsActive()
                                                        : true);
    return organization;
  }

  public Organization buildFromRequest(Organization organizationToBeUpdated,
      OrganizationUpdateRequest organizationUpdateRequest)
  {
    Organization organizationAfterUpdate = new Organization(organizationToBeUpdated);
    if (organizationUpdateRequest.getName() != null && StringUtils
        .isNotBlank(organizationUpdateRequest.getName()))
    {
      organizationAfterUpdate.setName(organizationUpdateRequest.getName());
    }
    if (organizationUpdateRequest.getIsActive() != null)
    {
      organizationAfterUpdate.setIsActive(organizationUpdateRequest.getIsActive());
    }
    return organizationAfterUpdate;
  }
}