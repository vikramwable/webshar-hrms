package org.webshar.hrms.controller;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.webshar.hrms.model.db.Role;
import org.webshar.hrms.request.roles.RoleCreateRequest;
import org.webshar.hrms.request.roles.RoleUpdateRequest;
import org.webshar.hrms.service.RoleService;
import org.webshar.hrms.service.exception.ServiceException;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

  @Autowired
  private RoleService roleService;

  @GetMapping(value = "/{roleId}")
  public Role getRoleById(@PathVariable("roleId") Long roleId)
      throws ServiceException {
    return roleService.getRoleById(roleId);
  }

  @PostMapping(value = "")
  public Role createRole(
      @Valid @RequestBody @NotNull RoleCreateRequest roleCreateRequest)
      throws ServiceException {
    return roleService.createRole(roleCreateRequest);
  }

  @PatchMapping(value = "/{roleId}")
  public Role updateRoleById(@PathVariable Long roleId,
      @NotNull @Valid @RequestBody
          RoleUpdateRequest roleUpdateRequest) throws ServiceException {
    return roleService.updateRole(roleId, roleUpdateRequest);
  }

  @DeleteMapping(value = "/{roleId}")
  public void deleteRoleById(@PathVariable Long roleId)
      throws ServiceException {
    roleService.deleteRoleById(roleId);
  }

  @GetMapping(value = "")
  public List<Role> getAllRoles() {
    return roleService.getAllRoles();
  }

}
