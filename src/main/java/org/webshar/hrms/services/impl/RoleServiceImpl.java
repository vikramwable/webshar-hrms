package org.webshar.hrms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webshar.hrms.dao.RoleRepository;
import org.webshar.hrms.dao.exceptions.EntityNotFoundException;
import org.webshar.hrms.dao.impl.RoleDaoImpl;
import org.webshar.hrms.models.db.Role;
import org.webshar.hrms.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService
{

  @Autowired
  RoleRepository roleRepository;

  @Override
  public Role getRole(Long roleId) throws EntityNotFoundException
  {
    return roleRepository.findById(roleId).orElse(null);
  }
}
