package org.webshar.hrms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webshar.hrms.constants.ErrorMessageConstants;
import org.webshar.hrms.model.builder.ResourceBuilder;
import org.webshar.hrms.model.db.Permission;
import org.webshar.hrms.model.db.Resource;
import org.webshar.hrms.repository.ResourceRepository;
import org.webshar.hrms.request.resource.ResourceCreateRequest;
import org.webshar.hrms.request.resource.ResourceUpdateRequest;
import org.webshar.hrms.service.exception.EntityAlreadyExistsException;
import org.webshar.hrms.service.exception.EntityNotFoundException;

@Service
public class ResourceService
{
  @Autowired
  ResourceRepository resourceRepository;

  @Autowired
  ResourceBuilder resourceBuilder;

  @Autowired
  PermissionService permissionService;

  @Autowired
  private ModelMapper modelMapper;

  public Resource getResourceById(Long resourceId) throws EntityNotFoundException
  {
    return resourceRepository.findById(resourceId)
        .orElseThrow(() -> new EntityNotFoundException(ErrorMessageConstants.RESOURCE_BY_ID_NOT_FOUND));
  }

  public Resource createResource(ResourceCreateRequest resourceCreateRequest)
      throws EntityAlreadyExistsException, EntityNotFoundException
  {
    List<Resource> resources = resourceRepository.findByName(resourceCreateRequest.getName());
    if (resources.isEmpty())
    {
      List<Permission> permissionList = validatePermissions(resourceCreateRequest.getPermissions());
      Resource resourceToCreate = resourceBuilder.buildFromRequest(resourceCreateRequest,permissionList);

      return resourceRepository.save(resourceToCreate);
    }
    else
    {
      throw new EntityAlreadyExistsException(ErrorMessageConstants.RESOURCE_DUPLICATE_NAME);
    }
  }

  public Resource updateResource(Long id, ResourceUpdateRequest resourceUpdateRequest) throws EntityNotFoundException {
    Resource resourceToUpdate = resourceRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException(ErrorMessageConstants.RESOURCE_BY_ID_NOT_FOUND));

    modelMapper.map(resourceUpdateRequest, resourceToUpdate);

    return resourceRepository.save(resourceToUpdate);
  }

  public void deleteResourceById(Long id) throws EntityNotFoundException
  {
    Optional<Resource> resourceToDelete = resourceRepository.findById(id);

    if (resourceToDelete.isPresent())
    {
      resourceRepository.deleteById(id);
    }
    else
      throw new EntityNotFoundException(ErrorMessageConstants.RESOURCE_BY_ID_NOT_FOUND);
  }

  public List<Resource> getAllResources()
  {
    return resourceRepository.findAll();
  }

  private List<Permission> validatePermissions(String[] permissions) throws EntityNotFoundException
  {
    List<Permission> permissionList = new ArrayList<>();
    if(permissions != null){
      for (String name : permissions)
      {
        permissionList.addAll(permissionService.getPermissionByName(name));
      }
    }
    return permissionList;
  }
}
