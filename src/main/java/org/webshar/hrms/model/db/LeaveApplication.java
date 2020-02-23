package org.webshar.hrms.model.db;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "leave_application", uniqueConstraints = @UniqueConstraint(columnNames = {"id"}))
public class LeaveApplication
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true, length = 11)
  @Getter
  @Setter
  private Long id;

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

  @OneToOne
  @Getter
  @Setter
  private Employee employee;

  @OneToOne
  @Getter
  @Setter
  private LeaveType leaveType;

  @OneToOne
  @Getter
  @Setter
  private LeaveStatus leaveStatus;

  public LeaveApplication(){

  }

  public  LeaveApplication(LeaveApplication leaveApplication){
    this.setId(leaveApplication.getId());
    this.setStartDate(leaveApplication.getStartDate());
    this.setEndDate(leaveApplication.getEndDate());
    this.setCreatedAt(leaveApplication.getCreatedAt());
    this.setEmployee(leaveApplication.getEmployee());
    this.setLeaveStatus(leaveApplication.getLeaveStatus());
    this.setLeaveType(leaveApplication.getLeaveType());
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
    LeaveApplication leaveApplication = (LeaveApplication) o;
    return id.equals(leaveApplication.id) &&
        startDate.equals(leaveApplication.startDate) &&
        endDate.equals(leaveApplication.endDate) &&
        leaveType.equals(leaveApplication.leaveType);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(id, startDate, endDate, leaveType);
  }
}
