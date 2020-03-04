package org.webshar.hrms.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.webshar.hrms.model.db.Resource;
import org.webshar.hrms.request.resource.ResourceCreateRequest;
import org.webshar.hrms.request.resource.ResourceUpdateRequest;
import org.webshar.hrms.service.ResourceService;
import org.webshar.hrms.service.exception.ServiceException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class ResourceController
{

  @Autowired
  private ResourceService resourceService;

  private static final Logger LOGGER = LoggerFactory.getLogger(ResourceController.class);

  @GetMapping(value = "{id}")
  public Resource getResourceById(@PathVariable("id") Long id)
      throws ServiceException
  {
    return resourceService.getResourceById(id);
  }

  @PostMapping(value = "")
  @ResponseStatus(HttpStatus.CREATED)
  public Resource createResource(
      @Valid @RequestBody @NotNull ResourceCreateRequest resourceCreateRequest)
      throws ServiceException
  {
    return resourceService.createResource(resourceCreateRequest);
  }

  @PatchMapping(value = "/{id}")
  public Resource updateResourceById(@PathVariable("id") Long id,
                                     @NotNull @Valid @RequestBody
          ResourceUpdateRequest resourceUpdateRequest) throws ServiceException
  {
    Assert.isTrue(id.equals(resourceUpdateRequest.getId()),
            "id and resourceUpdateRequest.id must be same");
    return resourceService.updateResource(resourceUpdateRequest);
  }

  @DeleteMapping(value = "/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteResourceById(@PathVariable Long id)
      throws ServiceException
  {
    resourceService.deleteResourceById(id);
  }

  @GetMapping(value = "")
  public List<Resource> getAllResources()
  {
    return resourceService.getAllResources();
  }
}
