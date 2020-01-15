package org.webshar.hrms.model.builder;

import java.util.UUID;
import org.springframework.stereotype.Component;
import org.webshar.hrms.model.db.Resource;
import org.webshar.hrms.request.resource.ResourceCreateRequest;

@Component
public class ResourceBuilder
{
  public Resource buildFromRequest(ResourceCreateRequest resourceCreateRequest)
  {
    Resource resourceToCreate = new Resource();
    resourceToCreate.setName(resourceCreateRequest.getName());
    resourceToCreate.setGuid(UUID.randomUUID());
    return  resourceToCreate;
  }
}
