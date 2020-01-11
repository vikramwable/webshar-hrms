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
import org.webshar.hrms.model.db.Organization;
import org.webshar.hrms.request.organization.OrganizationCreateRequest;
import org.webshar.hrms.request.organization.OrganizationUpdateRequest;
import org.webshar.hrms.response.BatchResponse;
import org.webshar.hrms.response.Response;
import org.webshar.hrms.service.OrganizationService;
import org.webshar.hrms.service.exception.EntityAlreadyExistsException;
import org.webshar.hrms.service.exception.EntityNotFoundException;
import org.webshar.hrms.service.exception.ServiceException;

@RestController
@RequestMapping("/")
public class OrganizationController
{

  @Autowired
  OrganizationService organizationService;

  private static final Logger LOGGER = Logger.getLogger(OrganizationController.class);

  @GetMapping(value = "/services/api/web/hrms/organizations/{organization_id}")
  public ResponseEntity<Response> getOrganizationById(
      @PathVariable("organization_id") Long organizationId)
      throws ServiceException
  {
    Response response = new Response();
    Organization organization = organizationService.getOrganizationById(organizationId);
    response.setEntity(organization);
    response.setMessage("Organization fetched");
    response.setStatus("OK");
    return ResponseEntity.ok(response);
  }

  @PostMapping(value = "/services/api/web/hrms/organizations")
  public ResponseEntity<Response> createOrganization(
      @Valid @RequestBody @NotNull OrganizationCreateRequest organizationCreateRequest)
      throws ServiceException
  {
    Response response = new Response();
    Organization organization = organizationService.createOrganization(organizationCreateRequest);
    response.setEntity(organization);
    response.setMessage("Organization added");
    response.setStatus("OK");
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PatchMapping(value = "/services/api/web/hrms/organizations/{organization_id}")
  public ResponseEntity<Response> updateOrganizationById(
      @PathVariable("organization_id") Long organizationId,
      @NotNull @Valid @RequestBody
          OrganizationUpdateRequest organizationUpdateRequest) throws ServiceException
  {
    Response response = new Response();
    response.setEntity(organizationService.updateOrganization(organizationUpdateRequest));
    response.setMessage("Organization updated");
    response.setStatus("OK");
    return ResponseEntity.ok(response);
  }

  @DeleteMapping(value = "/services/api/web/hrms/organizations/{organization_id}")
  public ResponseEntity<Void> deleteOrganizationById(
      @PathVariable("organization_id") Long organizationId)
      throws ServiceException
  {
    organizationService.deleteOrganizationById(organizationId);
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }

  @GetMapping(value = "/services/api/web/hrms/organizations")
  public ResponseEntity<BatchResponse> getAllOrganizations()
  {
    BatchResponse batchResponse = new BatchResponse();
    List<Organization> organizationsList;
    organizationsList = organizationService.getAllOrganizations();
    batchResponse.setEntities(Collections.singletonList(organizationsList));
    batchResponse.setTotalEntries(organizationsList.size());
    batchResponse.setMessage("Organizations fetched");
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
