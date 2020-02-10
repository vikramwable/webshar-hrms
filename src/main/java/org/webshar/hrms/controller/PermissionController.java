package org.webshar.hrms.controller;

import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.webshar.hrms.model.db.Permission;
import org.webshar.hrms.response.BatchResponse;
import org.webshar.hrms.response.Response;
import org.webshar.hrms.service.PermissionService;
import org.webshar.hrms.service.exception.EntityAlreadyExistsException;
import org.webshar.hrms.service.exception.EntityNotFoundException;
import org.webshar.hrms.service.exception.ServiceException;

@RestController
@RequestMapping("/")
public class PermissionController
{

  @Autowired
  PermissionService permissionService;

  private static final Logger LOGGER = LoggerFactory.getLogger(PermissionController.class);

  @GetMapping(value = "/services/api/web/hrms/permissions/{permission_id}")
  public ResponseEntity<Response> getPermissionById(@PathVariable("permission_id") Long permissionId)
      throws ServiceException
  {
    Response response = new Response();
    Permission permission = permissionService.getPermissionById(permissionId);
    response.setEntity(permission);
    response.setMessage("Permission fetched");
    response.setStatus("OK");
    return ResponseEntity.ok(response);
  }

  @GetMapping(value = "/services/api/web/hrms/permissions")
  public ResponseEntity<BatchResponse> getAllPermissions()
  {
    BatchResponse batchResponse = new BatchResponse();
    List<Permission> permissionList;
    permissionList = permissionService.getAllPermissions();
    batchResponse.setEntities(Collections.singletonList(permissionList));
    batchResponse.setTotalEntries(permissionList.size());
    batchResponse.setMessage("Permissions fetched");
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
