package org.webshar.hrms.services;

import org.webshar.hrms.dao.exceptions.EntityNotFoundException;
import org.webshar.hrms.models.db.Resource;

public interface ResourceService
{
  Resource getResourceById(Long resourceId) throws EntityNotFoundException;
}
