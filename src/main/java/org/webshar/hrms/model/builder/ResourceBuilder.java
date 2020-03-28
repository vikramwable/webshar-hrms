package org.webshar.hrms.model.builder;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;
import org.webshar.hrms.model.db.Permission;
import org.webshar.hrms.model.db.Resource;
import org.webshar.hrms.request.resource.ResourceCreateRequest;

@Component
public class ResourceBuilder {

  public Resource buildFromRequest(ResourceCreateRequest resourceCreateRequest, List<Permission> permissionList) {
    Resource resourceToCreate = new Resource();
    resourceToCreate.setName(resourceCreateRequest.getName());
    resourceToCreate.setGuid(UUID.randomUUID());
    resourceToCreate.setPermissions(permissionList);
    return resourceToCreate;
  }
}
