package org.webshar.hrms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webshar.hrms.constants.ErrorMessageConstants;
import org.webshar.hrms.model.db.LeaveStatus;
import org.webshar.hrms.repository.LeaveStatusRepository;
import org.webshar.hrms.service.exception.EntityNotFoundException;

@Service
public class LeaveStatusService
{

  @Autowired
  LeaveStatusRepository leaveStatusRepository;

  public LeaveStatus getLeaveStatusById(Long id) throws EntityNotFoundException
  {
    return leaveStatusRepository.findById(id)
        .orElseThrow(
            () -> new EntityNotFoundException(ErrorMessageConstants.LEAVE_STATUS_BY_ID_NOT_FOUND));
  }

  public  LeaveStatus getLeaveStatusByStatus(String status) throws EntityNotFoundException
  {
     LeaveStatus leaveStatuse =  leaveStatusRepository.findByStatus(status);

     if(leaveStatuse != null)
       return  leaveStatuse;
     else
        throw  new EntityNotFoundException(ErrorMessageConstants.LEAVE_STATUS_BY_STATUS_NAME_NOT_FOUND);
  }
}
