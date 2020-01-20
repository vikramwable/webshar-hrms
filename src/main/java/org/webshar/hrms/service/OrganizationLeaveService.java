package org.webshar.hrms.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webshar.hrms.constants.ErrorMessageConstants;
import org.webshar.hrms.model.builder.OrganizationLeaveBuilder;
import org.webshar.hrms.model.db.OrganizationLeave;
import org.webshar.hrms.repository.OrganizationLeaveRepository;
import org.webshar.hrms.request.organizationleave.OrganizationLeaveCreateRequest;
import org.webshar.hrms.request.organizationleave.OrganizationLeaveUpdateRequest;
import org.webshar.hrms.service.exception.EntityAlreadyExistsException;
import org.webshar.hrms.service.exception.EntityNotFoundException;

@Service
public class OrganizationLeaveService
{

  @Autowired
  OrganizationLeaveRepository organizationLeaveRepository;

  @Autowired
  OrganizationService organizationService;

  @Autowired
  LeaveTypeService leaveTypeService;

  @Autowired
  OrganizationLeaveBuilder organizationLeaveBuilder;

  public List<OrganizationLeave> getOrganizationLeaveByOrganizationId(Long organizationId)
  {
    return organizationLeaveRepository.findByOrganizationId(organizationId);
  }

  public OrganizationLeave createOrganizationLeave(
      OrganizationLeaveCreateRequest organizationLeaveCreateRequest)
      throws EntityAlreadyExistsException
  {
    List<OrganizationLeave> organizationLeaveList = organizationLeaveRepository
        .findByOrganizationIdAndLeaveTypeId(organizationLeaveCreateRequest.getOrganizationId(),
            organizationLeaveCreateRequest.getLeaveTypeId());
    if (organizationLeaveList.isEmpty())
    {
      OrganizationLeave organizationLeaveToCreate = organizationLeaveBuilder
          .buildFromRequest(organizationLeaveCreateRequest);
      return organizationLeaveRepository
          .save(organizationLeaveToCreate);
    }
    else
    {
      throw new EntityAlreadyExistsException(
          ErrorMessageConstants.ORGANIZATION_LEAVE_WITH_ORGANIZATION_AND_LEAVE_ID_EXISTS);
    }
  }

  public OrganizationLeave updateOrganizationLeave(
      OrganizationLeaveUpdateRequest organizationLeaveUpdateRequest)
      throws EntityNotFoundException
  {

    if (organizationLeaveUpdateRequest.getOrganizationId() != null)
    {
      organizationService.getOrganizationById(organizationLeaveUpdateRequest.getOrganizationId());
    }
    if (organizationLeaveUpdateRequest.getLeaveTypeId() != null)
    {
      leaveTypeService.getLeaveTypeById(organizationLeaveUpdateRequest.getLeaveTypeId());
    }

    Optional<OrganizationLeave> organizationLeaveToUpdate = organizationLeaveRepository
        .findById(organizationLeaveUpdateRequest.getId());

    if (organizationLeaveToUpdate.isPresent())
    {
      OrganizationLeave organizationLeaveAfterUpdate = organizationLeaveBuilder
          .buildFromRequest(organizationLeaveToUpdate.get(), organizationLeaveUpdateRequest);
      return organizationLeaveRepository.save(organizationLeaveAfterUpdate);
    }
    else
    {
      throw new EntityNotFoundException(ErrorMessageConstants.ORGANIZATION_LEAVE_BY_ID_NOT_NULL);
    }
  }

  public void deleteOrganizationLeaveById(Long id) throws EntityNotFoundException
  {
    Optional<OrganizationLeave> organizationLeaveToDelete = organizationLeaveRepository
        .findById(id);
    if (organizationLeaveToDelete.isPresent())
    {
      organizationLeaveRepository.delete(organizationLeaveToDelete.get());
    }
    else
    {
      throw new EntityNotFoundException(ErrorMessageConstants.ORGANIZATION_LEAVE_BY_ID_NOT_NULL);
    }
  }

  public List<OrganizationLeave> getAllOrganizationLeaves()
  {
    return organizationLeaveRepository.findAll();
  }
}