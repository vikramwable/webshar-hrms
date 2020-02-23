package org.webshar.hrms.response.employee.leave.allocation;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

public class EmployeeLeaveAllocationResponse
{
  @Getter
  @Setter
  private Long id;

  @Getter
  @Setter
  Employee employee;

  @Getter
  @Setter
  LeaveType leaveType;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  @Getter
  @Setter
  private Date startDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  @Getter
  @Setter
  private Date endDate;

  @Getter
  @Setter
  private Long allottedLeaves;

  @Getter
  @Setter
  private Long carriedLeaves;

  @Getter
  @Setter
  private Long additionalLeaves;

  @Getter
  @Setter
  private Long totalLeaves;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
  @Getter
  @Setter
  private Date createdAt;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
  @Getter
  @Setter
  private Date updatedAt;

  public  class Employee{

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

  public class LeaveType{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, length = 11)
    @Getter
    @Setter
    private Long id;

    @Column(name = "type", nullable = false, unique = true, length = 200)
    @Getter
    @Setter
    private String type;
  }
}
