package org.webshar.hrms.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webshar.hrms.constants.ErrorMessageConstants;
import org.webshar.hrms.model.builder.OrganizationBuilder;
import org.webshar.hrms.model.db.Organization;
import org.webshar.hrms.repository.OrganizationRepository;
import org.webshar.hrms.request.organization.OrganizationCreateRequest;
import org.webshar.hrms.request.organization.OrganizationUpdateRequest;
import org.webshar.hrms.service.exception.EntityAlreadyExistsException;
import org.webshar.hrms.service.exception.EntityNotFoundException;

@Service
public class OrganizationService {

  @Autowired
  OrganizationRepository organizationRepository;

  @Autowired
  OrganizationBuilder organizationBuilder;

  public Organization getOrganizationById(Long organizationId) throws EntityNotFoundException {
    return organizationRepository.findById(organizationId)
        .orElseThrow(
            () -> new EntityNotFoundException(ErrorMessageConstants.ORGANIZATION_BY_ID_NOT_FOUND));
  }

  public Organization createOrganization(OrganizationCreateRequest organizationCreateRequest)
      throws EntityAlreadyExistsException {
    List<Organization> organizations = organizationRepository
        .findByName(organizationCreateRequest.getName());
    if (organizations.isEmpty()) {
      Organization organizationToCreate = organizationBuilder
          .buildFromRequest(organizationCreateRequest);

      return organizationRepository.save(organizationToCreate);
    } else {
      throw new EntityAlreadyExistsException(ErrorMessageConstants.ORGANIZATION_DUPLICATE_NAME);
    }
  }

  public Organization updateOrganization(Long organizationId,
      OrganizationUpdateRequest organizationUpdateRequest)
      throws EntityNotFoundException {
    Organization organizationToUpdate = organizationRepository
        .findById(organizationId)
        .orElseThrow(() -> new EntityNotFoundException(ErrorMessageConstants.ORGANIZATION_BY_ID_NOT_FOUND));

    Organization updatedOrganization = organizationBuilder
        .buildFromRequest(organizationToUpdate, organizationUpdateRequest);
    updatedOrganization = organizationRepository.save(updatedOrganization);
    return updatedOrganization;
  }

  public void deleteOrganizationById(Long id) throws EntityNotFoundException {
    Optional<Organization> organizationToDelete = organizationRepository.findById(id);

    if (organizationToDelete.isPresent()) {
      organizationRepository.deleteById(id);
    } else {
      throw new EntityNotFoundException(ErrorMessageConstants.ORGANIZATION_BY_ID_NOT_FOUND);
    }
  }

  public List<Organization> getAllOrganizations() {
    return organizationRepository.findAll();
  }
}
