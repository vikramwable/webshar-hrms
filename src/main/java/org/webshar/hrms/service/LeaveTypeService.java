package org.webshar.hrms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webshar.hrms.constants.ErrorMessageConstants;
import org.webshar.hrms.model.db.LeaveType;
import org.webshar.hrms.repository.LeaveTypeRepository;
import org.webshar.hrms.service.exception.EntityNotFoundException;

@Service
public class LeaveTypeService
{

  @Autowired
  LeaveTypeRepository leaveTypeRepository;

  public LeaveType getLeaveTypeById(Long id) throws EntityNotFoundException
  {
    return leaveTypeRepository.findById(id)
        .orElseThrow(
            () -> new EntityNotFoundException(ErrorMessageConstants.LEAVE_TYPE_BY_ID_NOT_FOUND));
  }

}
