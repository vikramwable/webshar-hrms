package org.webshar.hrms.model.builder;

import java.util.UUID;
import org.springframework.stereotype.Component;
import org.webshar.hrms.model.db.Employee;
import org.webshar.hrms.model.db.LeaveAllocation;
import org.webshar.hrms.model.db.LeaveType;
import org.webshar.hrms.request.employee.leave.allocation.EmployeeLeaveAllocationCreateRequest;
import org.webshar.hrms.request.employee.leave.allocation.EmployeeLeaveAllocationUpdateRequest;

@Component
public class LeaveAllocationBuilder {

  public LeaveAllocation buildFromRequest(
      final EmployeeLeaveAllocationCreateRequest employeeLeaveAllocationCreateRequest,
      final Employee employee, final LeaveType leaveType) {
    LeaveAllocation leaveAllocation = new LeaveAllocation();
    leaveAllocation.setGuid(UUID.randomUUID());
    leaveAllocation.setEmployee(employee);
    leaveAllocation.setLeaveType(leaveType);
    leaveAllocation.setStartDate(employeeLeaveAllocationCreateRequest.getStartDate());
    leaveAllocation.setEndDate(employeeLeaveAllocationCreateRequest.getEndDate());
    leaveAllocation
        .setAllottedLeaves(employeeLeaveAllocationCreateRequest.getAllottedLeaves());
    leaveAllocation
        .setAdditionalLeaves(employeeLeaveAllocationCreateRequest.getAdditionalLeaves());
    leaveAllocation
        .setCarriedLeaves(employeeLeaveAllocationCreateRequest.getCarriedLeaves());
    leaveAllocation.setTotalLeaves(getTotalLeaves(leaveAllocation));
    return leaveAllocation;
  }

  public LeaveAllocation buildFromRequest(
      final EmployeeLeaveAllocationUpdateRequest employeeLeaveAllocationUpdateRequest,
      final LeaveAllocation leaveAllocationToBeUpdated, final Employee employee,
      final LeaveType leaveType) {
    LeaveAllocation leaveAllocationAfterUpdate = new LeaveAllocation(
        leaveAllocationToBeUpdated);

    if (employeeLeaveAllocationUpdateRequest.getEmployeeId() != null) {
      leaveAllocationAfterUpdate.setEmployee(employee);
    }
    if (employeeLeaveAllocationUpdateRequest.getLeaveTypeId() != null) {
      leaveAllocationAfterUpdate.setLeaveType(leaveType);
    }

    if (employeeLeaveAllocationUpdateRequest.getAllottedLeaves() != null) {
      leaveAllocationAfterUpdate
          .setAllottedLeaves(employeeLeaveAllocationUpdateRequest.getAllottedLeaves());
    }

    if (employeeLeaveAllocationUpdateRequest.getAdditionalLeaves() != null) {
      leaveAllocationAfterUpdate
          .setAdditionalLeaves(employeeLeaveAllocationUpdateRequest.getAdditionalLeaves());
    }

    if (employeeLeaveAllocationUpdateRequest.getCarriedLeaves() != null) {
      leaveAllocationAfterUpdate
          .setCarriedLeaves(employeeLeaveAllocationUpdateRequest.getCarriedLeaves());
    }

    if (employeeLeaveAllocationUpdateRequest.getStartDate() != null) {
      leaveAllocationAfterUpdate
          .setStartDate(employeeLeaveAllocationUpdateRequest.getStartDate());
    }

    if (employeeLeaveAllocationUpdateRequest.getEndDate() != null) {
      leaveAllocationAfterUpdate
          .setEndDate(employeeLeaveAllocationUpdateRequest.getEndDate());
    }

    leaveAllocationAfterUpdate
        .setTotalLeaves(getTotalLeaves(leaveAllocationAfterUpdate));

    return leaveAllocationAfterUpdate;
  }

  private Long getTotalLeaves(final LeaveAllocation leaveAllocation) {
    return leaveAllocation.getAdditionalLeaves() + leaveAllocation
        .getCarriedLeaves() + leaveAllocation.getAllottedLeaves();
  }
}
