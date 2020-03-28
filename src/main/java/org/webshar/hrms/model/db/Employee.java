package org.webshar.hrms.model.db;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;
import lombok.ToString;

@Entity
@Table(name = "employee")
@Getter
@Setter
@ToString(callSuper = true)
@JsonInclude(Include.NON_NULL)
public class Employee extends BaseModel
{

  @Column(name = "employee_id", nullable = false, unique = true, length = 11)
  private String empId;

  @Column(name = "organization_id", nullable = false, unique = true, length = 11)
  private Long organizationId;

  @Column(name = "first_name", nullable = false, length = 100)
  private String firstName;

  @Column(name = "middle_name", nullable = true, length = 100)
  private String middleName;

  @Column(name = "last_name", nullable = false, length = 100)
  private String lastName;

  @Column(name = "date_of_birth", nullable = false)
  private Date dateOfBirth;

  @Column(name = "is_active", nullable = false)
  private Boolean isActive;

  @Column(name = "email", nullable = false, length = 100)
  private String email;

  @Column(name = "joining_date", nullable = false, updatable = false)
  private Date joiningDate;

  @Column(name = "exit_date", nullable = true)
  private Date exitDate;

  @Column(name = "address", nullable = true, length = 300)
  private String address;

  @Column(name = "contact", nullable = false, length = 100)
  private String contact;

  @Column(name = "designation", nullable = false, length = 200)
  private String designation;

  public Employee()
  {

  }

  public Employee(Employee employee)
  {
    this.setId(employee.getId());
    this.setGuid(employee.getGuid());
    this.setEmpId(employee.getEmpId());
    this.setOrganizationId(employee.getOrganizationId());
    this.setFirstName(employee.getFirstName());
    this.setMiddleName(employee.getMiddleName());
    this.setLastName(employee.getLastName());
    this.setDateOfBirth(employee.getDateOfBirth());
    this.setIsActive(employee.getIsActive());
    this.setEmail(employee.getEmail());
    this.setJoiningDate(employee.getJoiningDate());
    this.setExitDate(employee.getExitDate());
    this.setAddress(employee.getAddress());
    this.setContact(employee.getContact());
    this.setDesignation(employee.getDesignation());
    this.setCreatedAt(employee.getCreatedAt());
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
    Employee employee = (Employee) o;
    return empId.equals(employee.empId) &&
        firstName.equals(employee.firstName) &&
        middleName.equals(employee.middleName) &&
        lastName.equals(employee.lastName) &&
        email.equals(employee.email);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(empId, firstName, middleName, lastName, email);
  }

  /**
   *
   */
  private static final long serialVersionUID = 1L;
}
