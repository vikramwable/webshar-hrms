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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.webshar.hrms.model.db.OrganizationLeave;
import org.webshar.hrms.request.organizationleave.OrganizationLeaveCreateRequest;
import org.webshar.hrms.request.organizationleave.OrganizationLeaveUpdateRequest;
import org.webshar.hrms.service.OrganizationLeaveService;
import org.webshar.hrms.service.exception.ServiceException;

@RestController
@RequestMapping("/api/organization-leaves")
public class OrganizationLeaveController {

  @Autowired
  OrganizationLeaveService organizationLeaveService;

  /**
   * GET /api/organization-leaves?organizationId={organizationId}
   */
  @GetMapping(value = "", params = "organizationId")
  public List<OrganizationLeave> getOrganizationLeavesByOrganizationId(
      @RequestParam(name = "organizationId", required = true) Long organizationId) {
    return organizationLeaveService.getOrganizationLeaveByOrganizationId(organizationId);
  }

  @PostMapping(value = "")
  @ResponseStatus(HttpStatus.CREATED)
  public OrganizationLeave addOrganizationLeave(
      @Valid @RequestBody @NotNull OrganizationLeaveCreateRequest organizationLeaveCreateRequest)
      throws ServiceException {
    return organizationLeaveService.createOrganizationLeave(organizationLeaveCreateRequest);
  }

  @PatchMapping(value = "/{organizationLeaveId}")
  public OrganizationLeave updateOrganizationLeave(@PathVariable Long organizationLeaveId,
      @NotNull @Valid @RequestBody OrganizationLeaveUpdateRequest organizationLeaveUpdateRequest)
      throws ServiceException {
    return organizationLeaveService.updateOrganizationLeave(organizationLeaveId, organizationLeaveUpdateRequest);
  }

  @DeleteMapping(value = "/{organizationLeaveId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteOrganizationLeaveById(
      @PathVariable Long organizationLeaveId)
      throws ServiceException {
    organizationLeaveService.deleteOrganizationLeaveById(organizationLeaveId);
  }

  @GetMapping(value = "")
  public List<OrganizationLeave> getAllOrganizationLeaves() {
    return organizationLeaveService.getAllOrganizationLeaves();
  }
}
