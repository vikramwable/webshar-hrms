package org.webshar.hrms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.webshar.hrms.model.db.Role;
import org.webshar.hrms.request.roles.RoleCreateRequest;
import org.webshar.hrms.request.roles.RoleUpdateRequest;
import org.webshar.hrms.service.RoleService;
import org.webshar.hrms.service.exception.ServiceException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController
{

  @Autowired
  private RoleService roleService;

  private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

  @GetMapping(value = "/{roleId}")
  public Role getRoleById(@PathVariable("roleId") Long roleId)
      throws ServiceException
  {
    return roleService.getRoleById(roleId);
  }

  @PostMapping(value = "")
  public Role createRole(
      @Valid @RequestBody @NotNull RoleCreateRequest roleCreateRequest)
      throws ServiceException
  {
    return roleService.createRole(roleCreateRequest);
  }

  @PatchMapping(value = "/{roleId}")
  public Role updateRoleById(@PathVariable Long roleId,
                             @NotNull @Valid @RequestBody
          RoleUpdateRequest roleUpdateRequest) throws ServiceException
  {
    return roleService.updateRole(roleId, roleUpdateRequest);
  }

  @DeleteMapping(value = "/{roleId}")
  public void deleteRoleById(@PathVariable Long roleId)
      throws ServiceException
  {
    roleService.deleteRoleById(roleId);
  }

  @GetMapping(value = "")
  public List<Role> getAllRoles()
  {
    return roleService.getAllRoles();
  }

}
