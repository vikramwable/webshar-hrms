package org.webshar.hrms.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webshar.hrms.constants.ErrorMessageConstants;
import org.webshar.hrms.repository.RoleRepository;
import org.webshar.hrms.service.exception.EntityAlreadyExistsException;
import org.webshar.hrms.service.exception.EntityNotFoundException;
import org.webshar.hrms.model.builder.RoleBuilder;
import org.webshar.hrms.model.db.Role;
import org.webshar.hrms.request.roles.RoleCreateRequest;
import org.webshar.hrms.request.roles.RoleUpdateRequest;

@Service
public class RoleService
{
  @Autowired
  RoleRepository roleRepository;

  @Autowired
  RoleBuilder roleBuilder;

  public Role getRoleById(Long roleId) throws EntityNotFoundException
  {
    return roleRepository.findById(roleId)
        .orElseThrow(() -> new EntityNotFoundException(ErrorMessageConstants.ROLE_BY_ID_NOT_FOUND));
  }

  public Role createRole(RoleCreateRequest roleCreateRequest)
      throws EntityAlreadyExistsException
  {
    List<Role> roles = roleRepository.findByName(roleCreateRequest.getName());
    if (roles.isEmpty())
    {
      Role roleToCreate = roleBuilder.buildFromRequest(roleCreateRequest);

      return roleRepository.save(roleToCreate);
    }
    else
    {
      throw new EntityAlreadyExistsException(ErrorMessageConstants.ROLE_DUPLICATE_NAME);
    }
  }

  public Role updateRole(RoleUpdateRequest roleUpdateRequest) throws EntityNotFoundException
  {
    Optional<Role> roleToUpdate = roleRepository.findById(roleUpdateRequest.getId());

    if(roleToUpdate.isPresent()){
      Role updatedRole = roleToUpdate.get();
      updatedRole.setName(roleUpdateRequest.getName());
      updatedRole = roleRepository.save(updatedRole);
      return updatedRole;
    }
    else
      throw new EntityNotFoundException(ErrorMessageConstants.ROLE_BY_ID_NOT_FOUND);
  }

  public void deleteRoleById(Long id) throws EntityNotFoundException
  {
    Optional<Role> roleToDelete = roleRepository.findById(id);

    if (roleToDelete.isPresent())
    {
      roleRepository.deleteById(id);
    }
    else
    throw new EntityNotFoundException(ErrorMessageConstants.ROLE_BY_ID_NOT_FOUND);
  }

  public List<Role> getAllRoles()
  {
    return roleRepository.findAll();
  }
}