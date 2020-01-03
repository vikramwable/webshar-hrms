package org.webshar.hrms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.webshar.hrms.dao.ResourceRepository;
import org.webshar.hrms.dao.exceptions.EntityNotFoundException;
import org.webshar.hrms.models.db.Resource;
import org.webshar.hrms.services.ResourceService;

public class ResourceServiceImpl implements ResourceService
{

  @Autowired
  ResourceRepository resourceRepository;

  @Override
  public Resource getResourceById(Long resourceId) throws EntityNotFoundException
  {
    return resourceRepository.findById(resourceId).orElse(null);
  }

  public Resource createResource(Resource resource){
    return resourceRepository.save(resource);
  }

  
}
