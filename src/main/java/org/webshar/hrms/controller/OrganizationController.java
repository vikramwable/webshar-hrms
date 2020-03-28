package org.webshar.hrms.controller;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.webshar.hrms.model.db.Organization;
import org.webshar.hrms.request.organization.OrganizationCreateRequest;
import org.webshar.hrms.request.organization.OrganizationUpdateRequest;
import org.webshar.hrms.service.OrganizationService;
import org.webshar.hrms.service.exception.ServiceException;

@RestController
@RequestMapping("/api/organizations")
public class OrganizationController {

  @Autowired
  OrganizationService organizationService;

  @GetMapping(value = "/{organizationId}")
  public Organization getOrganizationById(@PathVariable Long organizationId)
      throws ServiceException {
    return organizationService.getOrganizationById(organizationId);
  }

  @PostMapping(value = "")
  @ResponseStatus(HttpStatus.CREATED)
  public Organization createOrganization(
      @Valid @RequestBody @NotNull OrganizationCreateRequest organizationCreateRequest)
      throws ServiceException {
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
  public void deleteOrganizationById(@PathVariable Long organizationId) throws ServiceException {
    organizationService.deleteOrganizationById(organizationId);
  }

  @GetMapping(value = "")
  public List<Organization> getAllOrganizations() {
    return organizationService.getAllOrganizations();
  }
}
