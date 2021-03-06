package org.webshar.hrms.response.employee.leave.allocation;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class LeaveAllocationResponse {

  Employee employee;
  LeaveType leaveType;
  private Long id;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate startDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private LocalDate endDate;

  private Long allottedLeaves;

  private Long carriedLeaves;

  private Long additionalLeaves;

  private Long totalLeaves;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime createdAt;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime updatedAt;
}

@Data
class Employee {

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

@Data
class LeaveType {

  private Long id;

  private String type;
}