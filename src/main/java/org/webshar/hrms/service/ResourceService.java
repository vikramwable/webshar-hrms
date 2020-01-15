package org.webshar.hrms.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webshar.hrms.constants.ErrorMessageConstants;
import org.webshar.hrms.model.builder.ResourceBuilder;
import org.webshar.hrms.repository.ResourceRepository;
import org.webshar.hrms.request.resource.ResourceCreateRequest;
import org.webshar.hrms.request.resource.ResourceUpdateRequest;
import org.webshar.hrms.service.exception.EntityAlreadyExistsException;
import org.webshar.hrms.service.exception.EntityNotFoundException;
import org.webshar.hrms.model.db.Resource;

@Service
public class ResourceService
{
  @Autowired
  ResourceRepository resourceRepository;

  @Autowired
  ResourceBuilder resourceBuilder;

  public Resource getResourceById(Long resourceId) throws EntityNotFoundException
  {
    return resourceRepository.findById(resourceId)
        .orElseThrow(() -> new EntityNotFoundException(ErrorMessageConstants.RESOURCE_BY_ID_NOT_FOUND));
  }

  public Resource createResource(ResourceCreateRequest resourceCreateRequest)
      throws EntityAlreadyExistsException
  {
    List<Resource> resources = resourceRepository.findByName(resourceCreateRequest.getName());
    if (resources.isEmpty())
    {
      Resource resourceToCreate = resourceBuilder.buildFromRequest(resourceCreateRequest);

      return resourceRepository.save(resourceToCreate);
    }
    else
    {
      throw new EntityAlreadyExistsException(ErrorMessageConstants.RESOURCE_DUPLICATE_NAME);
    }
  }

  public Resource updateResource(ResourceUpdateRequest resourceUpdateRequest) throws EntityNotFoundException
  {
    Optional<Resource> resourceToUpdate = resourceRepository.findById(resourceUpdateRequest.getId());

    if(resourceToUpdate.isPresent()){
      Resource updatedResource = resourceToUpdate.get();
      updatedResource.setName(resourceUpdateRequest.getName());
      updatedResource = resourceRepository.save(updatedResource);
      return updatedResource;
    }
    else
      throw new EntityNotFoundException(ErrorMessageConstants.RESOURCE_BY_ID_NOT_FOUND);
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

}
