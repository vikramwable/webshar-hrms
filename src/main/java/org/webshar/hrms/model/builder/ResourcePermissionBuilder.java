package org.webshar.hrms.model.builder;

import org.springframework.stereotype.Component;
import org.webshar.hrms.model.db.ResourcePermission;
import org.webshar.hrms.request.resourcepermission.ResourcePermissionCreateRequest;
import org.webshar.hrms.request.resourcepermission.ResourcePermissionUpdateRequest;

@Component
public class ResourcePermissionBuilder
{

  public ResourcePermission buildFromRequest(
      ResourcePermissionCreateRequest resourcePermissionCreateRequest)
  {
    ResourcePermission resourcePermission = new ResourcePermission();
    resourcePermission.setResourceId(resourcePermissionCreateRequest.getResourceId());
    resourcePermission.setPermissionId(resourcePermissionCreateRequest.getPermissionId());
    return resourcePermission;
  }

  public ResourcePermission buildFromRequest(ResourcePermission resourcePermissionToUpdate,
      ResourcePermissionUpdateRequest resourcePermissionUpdateRequest)
  {

    ResourcePermission resourcePermissionAfterUpdate = new ResourcePermission(
        resourcePermissionToUpdate);

    if (resourcePermissionUpdateRequest.getResourceId() != null)
    {
      resourcePermissionAfterUpdate.setResourceId(resourcePermissionUpdateRequest.getResourceId());
    }
    if (resourcePermissionUpdateRequest.getPermissionId() != null)
    {
      resourcePermissionAfterUpdate
          .setPermissionId(resourcePermissionUpdateRequest.getPermissionId());
    }
    return resourcePermissionAfterUpdate;
  }
}
