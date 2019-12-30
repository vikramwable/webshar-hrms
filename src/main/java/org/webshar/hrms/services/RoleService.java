package org.webshar.hrms.services;

import org.webshar.hrms.dao.exceptions.EntityNotFoundException;
import org.webshar.hrms.models.db.Role;

public interface RoleService
{
  Role getRole(Long roleGuid) throws EntityNotFoundException;
}
