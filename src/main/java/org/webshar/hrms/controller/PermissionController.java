package org.webshar.hrms.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.webshar.hrms.model.db.Permission;
import org.webshar.hrms.service.PermissionService;
import org.webshar.hrms.service.exception.ServiceException;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

  @Autowired
  PermissionService permissionService;

  @GetMapping(value = "/{id}")
  public Permission getPermissionById(@PathVariable Long id)
      throws ServiceException {
    return permissionService.getPermissionById(id);
  }

  @GetMapping(value = "")
  public List<Permission> getAllPermissions() {
    return permissionService.getAllPermissions();
  }
}
