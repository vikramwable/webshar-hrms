package org.webshar.hrms.model.builder;

import java.util.UUID;
import org.springframework.stereotype.Component;
import org.webshar.hrms.model.db.Role;
import org.webshar.hrms.request.roles.RoleCreateRequest;

@Component
public class RoleBuilder
{
  public Role buildFromRequest(RoleCreateRequest roleCreateRequest)
  {
     Role roleToCreate = new Role();
     roleToCreate.setName(roleCreateRequest.getName());
     roleToCreate.setGuid(UUID.randomUUID());
     return  roleToCreate;
  }
}
