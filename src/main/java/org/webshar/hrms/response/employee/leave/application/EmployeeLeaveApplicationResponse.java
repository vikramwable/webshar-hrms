package org.webshar.hrms.response.employee.leave.application;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.persistence.Column;
import lombok.Getter;
import lombok.Setter;

public class EmployeeLeaveApplicationResponse
{
  @Getter
  @Setter
  private Long id;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  @Getter
  @Setter
  private Date startDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  @Getter
  @Setter
  private Date endDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
  @Getter
  @Setter
  private Date createdAt;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
  @Getter
  @Setter
  private Date updatedAt;


  @Getter
  @Setter
  private Employee employee;


  @Getter
  @Setter
  private LeaveType leaveType;

  @Getter
  @Setter
  private LeaveStatus leaveStatus;

}
class Employee
{

  @Getter
  @Setter
  private Long id;

  @Column(name = "emp_id", nullable = false, unique = true, length = 11)
  @Getter
  @Setter
  private Long employeeId;

  @Getter
  @Setter
  private Long organizationId;

  @Column(name = "first_name", nullable = false, length = 100)
  @Getter
  @Setter
  private String firstName;

  @Column(name = "middle_name", nullable = true, length = 100)
  @Getter
  @Setter
  private String middleName;

  @Column(name = "last_name", nullable = false, length = 100)
  @Getter
  @Setter
  private String lastName;

  @Column(name = "is_active", nullable = false)
  @Getter
  @Setter
  private Boolean isActive;

  @Column(name = "email", nullable = false, length = 100)
  @Getter
  @Setter
  private String email;

  @Column(name = "contact", nullable = false, length = 100)
  @Getter
  @Setter
  private String contact;

  @Column(name = "designation", nullable = false, length = 200)
  @Getter
  @Setter
  private String designation;
}

class LeaveType
{

  @Getter
  @Setter
  private Long id;

  @Getter
  @Setter
  private String type;
}

class LeaveStatus
{

  @Getter
  @Setter
  private Long id;

  @Getter
  @Setter
  private String status;
}