package org.webshar.hrms.response.employee.leave.allocation;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LeaveAllocationResponse
{

  private Long id;

  Employee employee;

  LeaveType leaveType;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date startDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date endDate;

  private Long allottedLeaves;

  private Long carriedLeaves;

  private Long additionalLeaves;

  private Long totalLeaves;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
  private Date createdAt;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
  private Date updatedAt;
}

@Getter
@Setter
class Employee
{
  private Long id;

  private Long employeeId;

  private Long organizationId;

  private String firstName;

  private String middleName;

  private String lastName;

  private Boolean isActive;

  private String email;

  private String contact;

  private String designation;
}

@Getter
@Setter
class LeaveType
{
  private Long id;

  private String type;
}