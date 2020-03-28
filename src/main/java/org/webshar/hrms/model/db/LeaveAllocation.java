package org.webshar.hrms.model.db;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;
import lombok.ToString;

@Entity
@Table(name = "leave_allocation")
@JsonInclude(Include.NON_NULL)
@Getter
@Setter
@ToString(callSuper = true)
public class LeaveAllocation extends BaseModel
{
  @OneToOne
  Employee employee;

  @OneToOne
  LeaveType leaveType;

  @Column(name = "start_date", nullable = false)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate startDate;

  @Column(name = "end_date", nullable = false)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate endDate;

  @Column(name = "allotted_leaves", nullable = false)
  private Long allottedLeaves;

  @Column(name = "carried_leaves", nullable = false)
  private Long carriedLeaves;

  @Column(name = "additional_leaves", nullable = false)
  private Long additionalLeaves;

  @Column(name = "total_leaves", nullable = false)
  private Long totalLeaves;

  public LeaveAllocation()
  {
  }

  public LeaveAllocation(LeaveAllocation leaveAllocation)
  {
    this.setId(leaveAllocation.getId());
    this.setGuid(leaveAllocation.getGuid());
    this.setLeaveType(leaveAllocation.getLeaveType());
    this.setEmployee(leaveAllocation.getEmployee());
    this.setAdditionalLeaves(leaveAllocation.getAdditionalLeaves());
    this.setAllottedLeaves(leaveAllocation.getAllottedLeaves());
    this.setCarriedLeaves(leaveAllocation.getCarriedLeaves());
    this.setTotalLeaves(leaveAllocation.getTotalLeaves());
    this.setStartDate(leaveAllocation.getStartDate());
    this.setEndDate(leaveAllocation.getEndDate());
    this.setCreatedAt(leaveAllocation.getCreatedAt());
    this.setUpdatedAt(leaveAllocation.getUpdatedAt());
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o)
    {
      return true;
    }
    if (o == null || getClass() != o.getClass())
    {
      return false;
    }
    LeaveAllocation that = (LeaveAllocation) o;
    return this.getId().equals(that.getId()) &&
        employee.equals(that.employee) &&
        leaveType.equals(that.leaveType) &&
        startDate.equals(that.startDate) &&
        endDate.equals(that.endDate);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(this.getId(), employee, leaveType, startDate, endDate);
  }

}
