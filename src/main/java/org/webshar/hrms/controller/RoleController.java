package org.webshar.hrms.controller;

import java.util.Collections;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.webshar.hrms.service.exception.EntityAlreadyExistsException;
import org.webshar.hrms.service.exception.EntityNotFoundException;
import org.webshar.hrms.model.db.Role;
import org.webshar.hrms.request.roles.RoleCreateRequest;
import org.webshar.hrms.request.roles.RoleUpdateRequest;
import org.webshar.hrms.response.BatchResponse;
import org.webshar.hrms.response.Response;
import org.webshar.hrms.service.RoleService;
import org.webshar.hrms.service.exception.ServiceException;

@RestController
@RequestMapping("/")
public class RoleController
{

  @Autowired
  RoleService roleService;

  private static final Logger LOGGER = Logger.getLogger(RoleController.class);

  @GetMapping(value = "/services/api/web/hrms/roles/{role_id}")
  public ResponseEntity<Response> getRoleById(@PathVariable("role_id") Long roleId)
      throws ServiceException
  {
    Response response = new Response();
    Role role = roleService.getRoleById(roleId);
    response.setEntity(role);
    response.setMessage("Role fetched");
    response.setStatus("OK");
    return ResponseEntity.ok(response);
  }

  @PostMapping(value = "/services/api/web/hrms/roles")
  public ResponseEntity<Response> createRole(
      @Valid @RequestBody @NotNull RoleCreateRequest roleCreateRequest)
      throws ServiceException
  {
    Response response = new Response();
    Role role = roleService.createRole(roleCreateRequest);
    response.setEntity(role);
    response.setMessage("Role added");
    response.setStatus("OK");
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PatchMapping(value = "/services/api/web/hrms/roles/{role_id}")
  public ResponseEntity<Response> updateRoleById(@PathVariable("role_id") Long roleId,
      @NotNull @Valid @RequestBody
          RoleUpdateRequest roleUpdateRequest) throws ServiceException
  {
    Response response = new Response();
    response.setEntity(roleService.updateRole(roleUpdateRequest));
    response.setMessage("Role updated");
    response.setStatus("OK");
    return ResponseEntity.ok(response);
  }

  @DeleteMapping(value = "/services/api/web/hrms/roles/{role_id}")
  public ResponseEntity<Void> deleteRoleById(@PathVariable("role_id") Long roleId)
      throws ServiceException
  {
    roleService.deleteRoleById(roleId);
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }

  @GetMapping(value = "/services/api/web/hrms/roles")
  public ResponseEntity<BatchResponse> getAllRoles()
  {
    BatchResponse batchResponse = new BatchResponse();
    List<Role> roleList;
    roleList = roleService.getAllRoles();
    batchResponse.setEntities(Collections.singletonList(roleList));
    batchResponse.setTotalEntries(roleList.size());
    batchResponse.setMessage("Roles fetched");
    batchResponse.setStatus("OK");
    return ResponseEntity.ok(batchResponse);
  }

  @ExceptionHandler(EntityAlreadyExistsException.class)
  public ResponseEntity<Response> handleException(EntityAlreadyExistsException e)
  {
    LOGGER.error(e.getMessage(), e);
    Response response = new Response();
    response.setStatus("Already exists");
    response.setMessage(e.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Response> handleException(EntityNotFoundException e)
  {
    LOGGER.error(e.getMessage(), e);
    Response response = new Response();
    response.setStatus("Entity Not found");
    response.setMessage(e.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }
}
