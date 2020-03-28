package org.webshar.hrms.model.db;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "leave_application")
@Getter
@Setter
@ToString(callSuper = true)
public class LeaveApplication extends BaseModel {

  @Column(name = "start_date", nullable = false)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date startDate;

  @Column(name = "end_date", nullable = false)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date endDate;

  @OneToOne
  private Employee employee;

  @OneToOne
  private LeaveType leaveType;

  @OneToOne
  private LeaveStatus leaveStatus;

  public LeaveApplication() {

  }

  public LeaveApplication(LeaveApplication leaveApplication) {
    this.setId(leaveApplication.getId());
    this.setGuid(leaveApplication.getGuid());
    this.setStartDate(leaveApplication.getStartDate());
    this.setEndDate(leaveApplication.getEndDate());
    this.setCreatedAt(leaveApplication.getCreatedAt());
    this.setEmployee(leaveApplication.getEmployee());
    this.setLeaveStatus(leaveApplication.getLeaveStatus());
    this.setLeaveType(leaveApplication.getLeaveType());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LeaveApplication leaveApplication = (LeaveApplication) o;
    return this.getId().equals(leaveApplication.getId()) &&
        startDate.equals(leaveApplication.startDate) &&
        endDate.equals(leaveApplication.endDate) &&
        leaveType.equals(leaveApplication.leaveType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getId(), startDate, endDate, leaveType);
  }
}
