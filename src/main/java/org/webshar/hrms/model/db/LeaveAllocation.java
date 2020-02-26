package org.webshar.hrms.model.db;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "leave_allocation", uniqueConstraints = @UniqueConstraint(columnNames = {
    "id"}))

@JsonInclude(Include.NON_NULL)
public class LeaveAllocation
{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true, length = 11)
  @Getter
  @Setter
  private Long id;

  @OneToOne
  @Getter
  @Setter
  Employee employee;

  @OneToOne
  @Getter
  @Setter
  LeaveType leaveType;

  @Column(name = "start_date", nullable = false)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  @Getter
  @Setter
  private Date startDate;

  @Column(name = "end_date", nullable = false)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  @Getter
  @Setter
  private Date endDate;

  @Column(name = "allotted_leaves", nullable = false)
  @Getter
  @Setter
  private Long allottedLeaves;

  @Column(name = "carried_leaves", nullable = false)
  @Getter
  @Setter
  private Long carriedLeaves;

  @Column(name = "additional_leaves", nullable = false)
  @Getter
  @Setter
  private Long additionalLeaves;

  @Column(name = "total_leaves", nullable = false)
  @Getter
  @Setter
  private Long totalLeaves;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false, updatable = false)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
  @Getter
  @Setter
  private Date createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at", nullable = false)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
  @Getter
  @Setter
  private Date updatedAt;

  public LeaveAllocation()
  {
  }

  public LeaveAllocation(LeaveAllocation leaveAllocation)
  {
    this.setId(leaveAllocation.getId());
    this.setLeaveType(leaveAllocation.getLeaveType());
    this.setEmployee(leaveAllocation.getEmployee());
    this.setAdditionalLeaves(leaveAllocation.getAdditionalLeaves());
    this.setAllottedLeaves(leaveAllocation.getAllottedLeaves());
    this.setCarriedLeaves(leaveAllocation.getCarriedLeaves());
    this.setTotalLeaves(leaveAllocation.getTotalLeaves());
    this.setStartDate(leaveAllocation.getStartDate());
    this.setEndDate(leaveAllocation.getEndDate());
    this.setCreatedAt(leaveAllocation.getCreatedAt());
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
    return id.equals(that.id) &&
        employee.equals(that.employee) &&
        leaveType.equals(that.leaveType) &&
        startDate.equals(that.startDate) &&
        endDate.equals(that.endDate);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(id, employee, leaveType, startDate, endDate);
  }

}
