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
import org.webshar.hrms.model.db.ResourcePermission;
import org.webshar.hrms.request.resourcepermission.ResourcePermissionCreateRequest;
import org.webshar.hrms.request.resourcepermission.ResourcePermissionUpdateRequest;
import org.webshar.hrms.response.BatchResponse;
import org.webshar.hrms.response.Response;
import org.webshar.hrms.service.ResourcePermissionService;
import org.webshar.hrms.service.exception.EntityAlreadyExistsException;
import org.webshar.hrms.service.exception.EntityNotFoundException;
import org.webshar.hrms.service.exception.ServiceException;

@RestController
@RequestMapping("/")
public class ResourcePermissionController
{

  @Autowired
  ResourcePermissionService resourcePermissionService;

  private static final Logger LOGGER = LoggerFactory.getLogger(ResourcePermissionController.class);

  @GetMapping(value = "/services/api/web/hrms/resource-permissions/{resource_permission_id}")
  public ResponseEntity<Response> getResourcePermissionById(
      @PathVariable("resource_permission_id") Long resourcePermissionId)
      throws ServiceException
  {
    Response response = new Response();
    ResourcePermission resourcePermission = resourcePermissionService
        .getResourcePermissionById(resourcePermissionId);
    response.setEntity(resourcePermission);
    response.setMessage("Resource_Permissions fetched");
    response.setStatus("OK");
    return ResponseEntity.ok(response);
  }

  @PostMapping(value = "/services/api/web/hrms/resource-permissions")
  public ResponseEntity<Response> createResourcePermission(
      @Valid @RequestBody @NotNull ResourcePermissionCreateRequest resourcePermissionCreateRequest)
      throws ServiceException
  {
    Response response = new Response();
    ResourcePermission resourcePermission = resourcePermissionService
        .createResourcePermission(resourcePermissionCreateRequest);
    response.setEntity(resourcePermission);
    response.setMessage("Resource_Permissions added");
    response.setStatus("OK");
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PatchMapping(value = "/services/api/web/hrms/resource-permissions/{resource_permission_id}")
  public ResponseEntity<Response> updateResourcePermissionById(
      @PathVariable("resource_permission_id") Long resourcePermissionId,
      @NotNull @Valid @RequestBody
          ResourcePermissionUpdateRequest resourcePermissionUpdateRequest) throws ServiceException
  {
    Response response = new Response();
    response.setEntity(
        resourcePermissionService.updateResourcePermission(resourcePermissionUpdateRequest));
    response.setMessage("Resource_Permissions updated");
    response.setStatus("OK");
    return ResponseEntity.ok(response);
  }

  @DeleteMapping(value = "/services/api/web/hrms/resource-permissions/{resource_permission_id}")
  public ResponseEntity<Void> deleteResourcePermissionById(
      @PathVariable("resource_permission_id") Long resourcePermissionId)
      throws ServiceException
  {
    resourcePermissionService.deleteResourcePermissionById(resourcePermissionId);
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }

  @GetMapping(value = "/services/api/web/hrms/resource-permissions")
  public ResponseEntity<BatchResponse> getAllResourcePermissions()
  {
    BatchResponse batchResponse = new BatchResponse();
    List<ResourcePermission> resourcePermissionList;
    resourcePermissionList = resourcePermissionService.getAllResourcePermissions();
    batchResponse.setEntities(Collections.singletonList(resourcePermissionList));
    batchResponse.setTotalEntries(resourcePermissionList.size());
    batchResponse.setMessage("Resource_Permissions fetched");
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
