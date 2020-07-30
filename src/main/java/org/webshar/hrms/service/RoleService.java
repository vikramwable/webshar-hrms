package org.webshar.hrms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webshar.hrms.constants.ErrorMessageConstants;
import org.webshar.hrms.model.builder.RoleBuilder;
import org.webshar.hrms.model.db.Permission;
import org.webshar.hrms.model.db.Role;
import org.webshar.hrms.repository.RoleRepository;
import org.webshar.hrms.request.roles.RoleCreateRequest;
import org.webshar.hrms.request.roles.RoleUpdateRequest;
import org.webshar.hrms.service.exception.EntityAlreadyExistsException;
import org.webshar.hrms.service.exception.EntityNotFoundException;

@Service
public class RoleService {

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PermissionService permissionService;

  @Autowired
  RoleBuilder roleBuilder;

  @Autowired
  private ModelMapper modelMapper;

  public Role getRoleById(Long roleId) throws EntityNotFoundException {
    return roleRepository.findById(roleId)
        .orElseThrow(() -> new EntityNotFoundException(ErrorMessageConstants.ROLE_BY_ID_NOT_FOUND));
  }

  public Role createRole(RoleCreateRequest roleCreateRequest)
      throws EntityAlreadyExistsException, EntityNotFoundException {
    List<Role> roles = roleRepository.findByName(roleCreateRequest.getName());

    if (roles.isEmpty()) {
      List<Permission> permissionList = validatePermissions(roleCreateRequest.getPermissions());
      Role roleToCreate = roleBuilder.buildFromRequest(roleCreateRequest, permissionList);
      return roleRepository.save(roleToCreate);
    } else {
      throw new EntityAlreadyExistsException(ErrorMessageConstants.ROLE_DUPLICATE_NAME);
    }
  }

  public Role updateRole(Long roleId, RoleUpdateRequest roleUpdateRequest) throws EntityNotFoundException {
    Role roleToUpdate = roleRepository.findById(roleId)
        .orElseThrow(() -> new EntityNotFoundException(ErrorMessageConstants.ROLE_BY_ID_NOT_FOUND));

    modelMapper.map(roleUpdateRequest, roleToUpdate);

    return roleRepository.save(roleToUpdate);
  }

  public void deleteRoleById(Long id) throws EntityNotFoundException {
    Optional<Role> roleToDelete = roleRepository.findById(id);

    if (roleToDelete.isPresent()) {
      roleRepository.deleteById(id);
    } else {
      throw new EntityNotFoundException(ErrorMessageConstants.ROLE_BY_ID_NOT_FOUND);
    }
  }

  public List<Role> getAllRoles() {
    return roleRepository.findAll();
  }

  private List<Permission> validatePermissions(String[] permissions) throws EntityNotFoundException {
    List<Permission> permissionList = new ArrayList<>();
    if (permissions != null) {
      for (String name : permissions) {
        permissionList.addAll(permissionService.getPermissionByName(name));
      }
    }
    return permissionList;
  }
}