package org.webshar.hrms.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webshar.hrms.constants.ErrorMessageConstants;
import org.webshar.hrms.model.builder.RolePermissionBuilder;
import org.webshar.hrms.model.db.RolePermission;
import org.webshar.hrms.repository.RolePermissionRepository;
import org.webshar.hrms.request.rolepermission.RolePermissionCreateRequest;
import org.webshar.hrms.request.rolepermission.RolePermissionUpdateRequest;
import org.webshar.hrms.service.exception.EntityAlreadyExistsException;
import org.webshar.hrms.service.exception.EntityNotFoundException;

@Service
public class RolePermissionService
{

  @Autowired
  RolePermissionRepository rolePermissionRepository;

  @Autowired
  RolePermissionBuilder rolePermissionBuilder;

  public RolePermission getRolePermissionById(Long rolePermissionId) throws EntityNotFoundException
  {
    return rolePermissionRepository.findById(rolePermissionId)
        .orElseThrow(
            () -> new EntityNotFoundException(
                ErrorMessageConstants.ROLE_PERMISSION_BY_ID_NOT_FOUND));
  }

  public RolePermission createRolePermission(
      RolePermissionCreateRequest rolePermissionCreateRequest)
      throws EntityAlreadyExistsException
  {
    List<RolePermission> rolePermissions = rolePermissionRepository
        .findByRoleIdAndPermissionId(rolePermissionCreateRequest.getRoleId(),
            rolePermissionCreateRequest.getPermissionId());

    if (rolePermissions.isEmpty())
    {
      RolePermission rolePermissionToCreate = rolePermissionBuilder
          .buildFromRequest(rolePermissionCreateRequest);
      return rolePermissionRepository.save(rolePermissionToCreate);
    }
    else
    {
      return rolePermissions.get(0);
    }
  }

  public RolePermission updateRolePermission(
      RolePermissionUpdateRequest rolePermissionUpdateRequest)
      throws EntityNotFoundException
  {
    Optional<RolePermission> rolePermissionToUpdate = rolePermissionRepository
        .findById(rolePermissionUpdateRequest.getId());

    if (rolePermissionToUpdate.isPresent())
    {
      RolePermission updatedRolePermission = rolePermissionBuilder
          .buildFromRequest(rolePermissionToUpdate.get(), rolePermissionUpdateRequest);
      updatedRolePermission = rolePermissionRepository.save(updatedRolePermission);
      return updatedRolePermission;
    }
    else
    {
      throw new EntityNotFoundException(ErrorMessageConstants.ROLE_PERMISSION_BY_ID_NOT_FOUND);
    }
  }

  public void deleteRolePermissionById(Long id) throws EntityNotFoundException
  {
    Optional<RolePermission> rolePermissionToDelete = rolePermissionRepository.findById(id);

    if (rolePermissionToDelete.isPresent())
    {
      rolePermissionRepository.deleteById(id);
    }
    else
    {
      throw new EntityNotFoundException(ErrorMessageConstants.ROLE_PERMISSION_BY_ID_NOT_FOUND);
    }
  }

  public List<RolePermission> getAllRolePermissions()
  {
    return rolePermissionRepository.findAll();
  }

}
