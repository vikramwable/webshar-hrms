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
import org.webshar.hrms.model.db.Resource;
import org.webshar.hrms.request.resource.ResourceCreateRequest;
import org.webshar.hrms.request.resource.ResourceUpdateRequest;
import org.webshar.hrms.response.BatchResponse;
import org.webshar.hrms.response.Response;
import org.webshar.hrms.service.ResourceService;
import org.webshar.hrms.service.exception.EntityAlreadyExistsException;
import org.webshar.hrms.service.exception.EntityNotFoundException;
import org.webshar.hrms.service.exception.ServiceException;

@RestController
@RequestMapping("/")
public class ResourceController
{

  @Autowired
  ResourceService resourceService;

  private static final Logger LOGGER = Logger.getLogger(ResourceController.class);

  @GetMapping(value = "/services/api/web/hrms/resources/{resource_id}")
  public ResponseEntity<Response> getResourceById(@PathVariable("resource_id") Long resourceId)
      throws ServiceException
  {
    Response response = new Response();
    Resource resource = resourceService.getResourceById(resourceId);
    response.setEntity(resource);
    response.setMessage("Resource fetched");
    response.setStatus("OK");
    return ResponseEntity.ok(response);
  }

  @PostMapping(value = "/services/api/web/hrms/resources")
  public ResponseEntity<Response> createResource(
      @Valid @RequestBody @NotNull ResourceCreateRequest resourceCreateRequest)
      throws ServiceException
  {
    Response response = new Response();
    Resource resource = resourceService.createResource(resourceCreateRequest);
    response.setEntity(resource);
    response.setMessage("Resource added");
    response.setStatus("OK");
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PatchMapping(value = "/services/api/web/hrms/resources/{resource_id}")
  public ResponseEntity<Response> updateResourceById(@PathVariable("resource_id") Long resourceId,
      @NotNull @Valid @RequestBody
          ResourceUpdateRequest resourceUpdateRequest) throws ServiceException
  {
    Response response = new Response();
    response.setEntity(resourceService.updateResource(resourceUpdateRequest));
    response.setMessage("Resource updated");
    response.setStatus("OK");
    return ResponseEntity.ok(response);
  }

  @DeleteMapping(value = "/services/api/web/hrms/resources/{resource_id}")
  public ResponseEntity<Void> deleteResourceById(@PathVariable("resource_id") Long resourceId)
      throws ServiceException
  {
    resourceService.deleteResourceById(resourceId);
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }

  @GetMapping(value = "/services/api/web/hrms/resources")
  public ResponseEntity<BatchResponse> getAllResources()
  {
    BatchResponse batchResponse = new BatchResponse();
    List<Resource> resourceList;
    resourceList = resourceService.getAllResources();
    batchResponse.setEntities(Collections.singletonList(resourceList));
    batchResponse.setTotalEntries(resourceList.size());
    batchResponse.setMessage("Resources fetched");
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
