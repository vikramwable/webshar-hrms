package org.webshar.hrms.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webshar.hrms.constants.ErrorMessageConstants;
import org.webshar.hrms.model.builder.ResourceBuilder;
import org.webshar.hrms.model.db.Permission;
import org.webshar.hrms.model.db.Resource;
import org.webshar.hrms.repository.PermissionRepository;
import org.webshar.hrms.repository.ResourceRepository;
import org.webshar.hrms.request.resource.ResourceCreateRequest;
import org.webshar.hrms.request.resource.ResourceUpdateRequest;
import org.webshar.hrms.service.exception.EntityAlreadyExistsException;
import org.webshar.hrms.service.exception.EntityNotFoundException;

@Service
public class PermissionService
{

  @Autowired
  PermissionRepository permissionRepository;

  public Permission getPermissionById(Long permissionId) throws EntityNotFoundException
  {
    return permissionRepository.findById(permissionId)
        .orElseThrow(
            () -> new EntityNotFoundException(ErrorMessageConstants.PERMISSION_BY_ID_NOT_FOUND));
  }

  public List<Permission> getPermissionByName(String name) throws EntityNotFoundException
  {
    List<Permission> permissionList = permissionRepository.findByName(name);
    if (permissionList.isEmpty())
    {
      throw new EntityNotFoundException(ErrorMessageConstants.PERMISSION_WITH_GIVEN_NAME_NOT_FOUND);
    }
    else
    {
      return permissionList;
    }
  }

  public List<Permission> getAllPermissions()
  {
    return permissionRepository.findAll();
  }


}
