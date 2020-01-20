package org.webshar.hrms.controller;

import java.util.Collections;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.webshar.hrms.model.db.RolePermission;
import org.webshar.hrms.request.rolepermission.RolePermissionCreateRequest;
import org.webshar.hrms.request.rolepermission.RolePermissionUpdateRequest;
import org.webshar.hrms.response.BatchResponse;
import org.webshar.hrms.response.Response;
import org.webshar.hrms.service.RolePermissionService;
import org.webshar.hrms.service.exception.EntityAlreadyExistsException;
import org.webshar.hrms.service.exception.EntityNotFoundException;
import org.webshar.hrms.service.exception.ServiceException;

@RestController
@RequestMapping("/")
public class RolePermissionController
{

  @Autowired
  RolePermissionService rolePermissionService;

  private static final Logger LOGGER = LoggerFactory.getLogger(RolePermissionController.class);

  @GetMapping(value = "/services/api/web/hrms/role-permissions/{role_permission_id}")
  public ResponseEntity<Response> getRolePermissionById(
      @PathVariable("role_permission_id") Long rolePermissionId)
      throws ServiceException
  {
    Response response = new Response();
    RolePermission rolePermission = rolePermissionService.getRolePermissionById(rolePermissionId);
    response.setEntity(rolePermission);
    response.setMessage("Role_Permission fetched");
    response.setStatus("OK");
    return ResponseEntity.ok(response);
  }

  @PostMapping(value = "/services/api/web/hrms/role-permissions")
  public ResponseEntity<Response> createRolePermission(
      @Valid @RequestBody @NotNull RolePermissionCreateRequest rolePermissionCreateRequest)
      throws ServiceException
  {
    Response response = new Response();
    RolePermission rolePermission = rolePermissionService
        .createRolePermission(rolePermissionCreateRequest);
    response.setEntity(rolePermission);
    response.setMessage("Role_Permission added");
    response.setStatus("OK");
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PatchMapping(value = "/services/api/web/hrms/role-permissions/{role_permission_id}")
  public ResponseEntity<Response> updateRolePermissionById(
      @PathVariable("role_permission_id") Long rolePermissionId,
      @NotNull @Valid @RequestBody
          RolePermissionUpdateRequest rolePermissionUpdateRequest) throws ServiceException
  {
    Response response = new Response();
    response.setEntity(rolePermissionService.updateRolePermission(rolePermissionUpdateRequest));
    response.setMessage("Role_Permission updated");
    response.setStatus("OK");
    return ResponseEntity.ok(response);
  }

  @DeleteMapping(value = "/services/api/web/hrms/role-permissions/{role_permission_id}")
  public ResponseEntity<Void> deleteRolePermissionById(
      @PathVariable("role_permission_id") Long rolePermissionId)
      throws ServiceException
  {
    rolePermissionService.deleteRolePermissionById(rolePermissionId);
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }

  @GetMapping(value = "/services/api/web/hrms/role-permissions")
  public ResponseEntity<BatchResponse> getAllRolePermissions()
  {
    BatchResponse batchResponse = new BatchResponse();
    List<RolePermission> rolePermissionList;
    rolePermissionList = rolePermissionService.getAllRolePermissions();
    batchResponse.setEntities(Collections.singletonList(rolePermissionList));
    batchResponse.setTotalEntries(rolePermissionList.size());
    batchResponse.setMessage("Role_Permissions fetched");
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
