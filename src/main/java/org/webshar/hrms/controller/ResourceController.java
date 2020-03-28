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
import org.webshar.hrms.model.db.Resource;
import org.webshar.hrms.request.resource.ResourceCreateRequest;
import org.webshar.hrms.request.resource.ResourceUpdateRequest;
import org.webshar.hrms.service.ResourceService;
import org.webshar.hrms.service.exception.ServiceException;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

  @Autowired
  private ResourceService resourceService;

  @GetMapping(value = "{id}")
  public Resource getResourceById(@PathVariable("id") Long id)
      throws ServiceException {
    return resourceService.getResourceById(id);
  }

  @PostMapping(value = "")
  @ResponseStatus(HttpStatus.CREATED)
  public Resource createResource(
      @Valid @RequestBody @NotNull ResourceCreateRequest resourceCreateRequest)
      throws ServiceException {
    return resourceService.createResource(resourceCreateRequest);
  }

  @PatchMapping(value = "/{id}")
  public Resource updateResourceById(@PathVariable("id") Long id,
      @NotNull @Valid @RequestBody ResourceUpdateRequest resourceUpdateRequest) throws ServiceException {
    return resourceService.updateResource(id, resourceUpdateRequest);
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteResourceById(@PathVariable Long id)
      throws ServiceException {
    resourceService.deleteResourceById(id);
  }

  @GetMapping(value = "")
  public List<Resource> getAllResources() {
    return resourceService.getAllResources();
  }
}
