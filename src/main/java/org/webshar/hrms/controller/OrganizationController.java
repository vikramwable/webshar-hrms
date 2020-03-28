package org.webshar.hrms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.webshar.hrms.model.db.Organization;
import org.webshar.hrms.request.organization.OrganizationCreateRequest;
import org.webshar.hrms.request.organization.OrganizationUpdateRequest;
import org.webshar.hrms.service.OrganizationService;
import org.webshar.hrms.service.exception.ServiceException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/organizations")
public class OrganizationController
{

  @Autowired
  OrganizationService organizationService;

  private static final Logger LOGGER = LoggerFactory.getLogger(OrganizationController.class);

  @GetMapping(value = "/{organizationId}")
  public Organization getOrganizationById(@PathVariable Long organizationId)
      throws ServiceException
  {
    return organizationService.getOrganizationById(organizationId);
  }

  @PostMapping(value = "")
  @ResponseStatus(HttpStatus.CREATED)
  public Organization createOrganization(
      @Valid @RequestBody @NotNull OrganizationCreateRequest organizationCreateRequest)
      throws ServiceException
  {
    return organizationService.createOrganization(organizationCreateRequest);
  }

  @PatchMapping(value = "/{organizationId}")
  public Organization updateOrganizationById(
      @PathVariable Long organizationId,
      @NotNull @Valid @RequestBody OrganizationUpdateRequest organizationUpdateRequest)
      throws ServiceException {
    return organizationService.updateOrganization(organizationId, organizationUpdateRequest);
  }

  @DeleteMapping(value = "{organizationId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteOrganizationById(@PathVariable Long organizationId) throws ServiceException
  {
    organizationService.deleteOrganizationById(organizationId);
  }

  @GetMapping(value = "")
  public List<Organization> getAllOrganizations()
  {
    return organizationService.getAllOrganizations();
  }
}
