package org.webshar.hrms.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webshar.hrms.constants.ErrorMessageConstants;
import org.webshar.hrms.model.builder.ResourcePermissionBuilder;
import org.webshar.hrms.model.db.Permission;
import org.webshar.hrms.model.db.Resource;
import org.webshar.hrms.model.db.ResourcePermission;
import org.webshar.hrms.repository.ResourcePermissionRepository;
import org.webshar.hrms.request.resourcepermission.ResourcePermissionCreateRequest;
import org.webshar.hrms.request.resourcepermission.ResourcePermissionUpdateRequest;
import org.webshar.hrms.service.exception.EntityAlreadyExistsException;
import org.webshar.hrms.service.exception.EntityNotFoundException;

@Service
public class ResourcePermissionService
{

  @Autowired
  ResourcePermissionRepository resourcePermissionRepository;

  @Autowired
  ResourcePermissionBuilder resourcePermissionBuilder;

  @Autowired
  ResourceService resourceService;

  @Autowired
  PermissionService permissionService;

  public ResourcePermission getResourcePermissionById(Long resourcePermissionId)
      throws EntityNotFoundException
  {
    return resourcePermissionRepository.findById(resourcePermissionId)
        .orElseThrow(
            () -> new EntityNotFoundException(
                ErrorMessageConstants.RESOURCE_PERMISSION_BY_ID_NOT_FOUND));
  }

  public ResourcePermission createResourcePermission(
      ResourcePermissionCreateRequest resourcePermissionCreateRequest)
      throws EntityNotFoundException
  {

    Resource resource = resourceService
        .getResourceById(resourcePermissionCreateRequest.getResourceId());
    Permission permission = permissionService
        .getPermissionById(resourcePermissionCreateRequest.getPermissionId());

    List<ResourcePermission> resourcePermissions = resourcePermissionRepository
        .findByResourceIdAndPermissionId(resourcePermissionCreateRequest.getResourceId(),
            resourcePermissionCreateRequest.getPermissionId());

    if (resourcePermissions.isEmpty())
    {
      ResourcePermission resourcePermissionToCreate = resourcePermissionBuilder
          .buildFromRequest(resourcePermissionCreateRequest);
      return resourcePermissionRepository.save(resourcePermissionToCreate);
    }
    else
    {
      return resourcePermissions.get(0);
    }
  }

  public ResourcePermission updateResourcePermission(
      ResourcePermissionUpdateRequest resourcePermissionUpdateRequest)
      throws EntityNotFoundException
  {
    if(resourcePermissionUpdateRequest.getResourceId() != null){
      Resource resource = resourceService
          .getResourceById(resourcePermissionUpdateRequest.getResourceId());
    }
    if(resourcePermissionUpdateRequest.getPermissionId() != null){
      Permission permission = permissionService
          .getPermissionById(resourcePermissionUpdateRequest.getPermissionId());
    }
    Optional<ResourcePermission> resourcePermissionToUpdate = resourcePermissionRepository
        .findById(resourcePermissionUpdateRequest.getId());

    if (resourcePermissionToUpdate.isPresent())
    {
      ResourcePermission updatedResourcePermission = resourcePermissionBuilder
          .buildFromRequest(resourcePermissionToUpdate.get(), resourcePermissionUpdateRequest);
      updatedResourcePermission = resourcePermissionRepository.save(updatedResourcePermission);
      return updatedResourcePermission;
    }
    else
    {
      throw new EntityNotFoundException(ErrorMessageConstants.RESOURCE_PERMISSION_BY_ID_NOT_FOUND);
    }
  }

  public void deleteResourcePermissionById(Long id) throws EntityNotFoundException
  {
    Optional<ResourcePermission> resourcePermissionToDelete = resourcePermissionRepository
        .findById(id);

    if (resourcePermissionToDelete.isPresent())
    {
      resourcePermissionRepository.deleteById(id);
    }
    else
    {
      throw new EntityNotFoundException(ErrorMessageConstants.RESOURCE_PERMISSION_BY_ID_NOT_FOUND);
    }
  }

  public List<ResourcePermission> getAllResourcePermissions()
  {
    return resourcePermissionRepository.findAll();
  }
}
