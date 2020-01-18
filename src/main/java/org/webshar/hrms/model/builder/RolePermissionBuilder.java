package org.webshar.hrms.model.builder;

import org.springframework.stereotype.Component;
import org.webshar.hrms.model.db.RolePermission;
import org.webshar.hrms.request.rolepermission.RolePermissionCreateRequest;
import org.webshar.hrms.request.rolepermission.RolePermissionUpdateRequest;

@Component
public class RolePermissionBuilder
{

  public RolePermission buildFromRequest(RolePermissionCreateRequest rolePermissionCreateRequest)
  {
    RolePermission rolePermission = new RolePermission();
    rolePermission.setRoleId(rolePermissionCreateRequest.getRoleId());
    rolePermission.setPermissionId(rolePermissionCreateRequest.getPermissionId());
    return rolePermission;
  }

  public RolePermission buildFromRequest(RolePermission rolePermissionToUpdate,
      RolePermissionUpdateRequest rolePermissionUpdateRequest)
  {

    RolePermission rolePermissionAfterUpdate = new RolePermission(rolePermissionToUpdate);

    if (rolePermissionUpdateRequest.getRoleId() != null)
    {
      rolePermissionAfterUpdate.setRoleId(rolePermissionUpdateRequest.getRoleId());
    }
    if (rolePermissionUpdateRequest.getPermissionId() != null)
    {
      rolePermissionAfterUpdate.setPermissionId(rolePermissionUpdateRequest.getPermissionId());
    }
    return rolePermissionAfterUpdate;
  }
}
