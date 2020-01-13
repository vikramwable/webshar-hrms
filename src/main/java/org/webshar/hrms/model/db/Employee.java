package org.webshar.hrms.model.db;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "employee", uniqueConstraints = @UniqueConstraint(columnNames = {"id"}))
public class Employee implements Serializable
{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true, length = 11)
  @Getter
  @Setter
  private Long id;

  @Column(name = "guid", nullable = false, unique = true, length = 11)
  @Getter
  @Setter
  private UUID guid;

  @Column(name = "emp_id", nullable = false, unique = true, length = 11)
  @Getter
  @Setter
  private Long employeeId;

  @Column(name = "org_id", nullable = false, unique = true, length = 11)
  @Getter
  @Setter
  private Long organizationId;

  @Column(name = "firstName", nullable = false, length = 100)
  @Getter
  @Setter
  private String firstName;

  @Column(name = "middleName", nullable = true, length = 100)
  @Getter
  @Setter
  private String middleName;

  @Column(name = "lastName", nullable = false, length = 100)
  @Getter
  @Setter
  private String lastName;

  @Column(name = "date_of_birth", nullable = false)
  @Getter
  @Setter
  private Date dateOfBirth;

  @Column(name = "isActive", nullable = false)
  @Getter
  @Setter
  private Boolean isActive;

  @Column(name = "email", nullable = false, length = 100)
  @Getter
  @Setter
  private String email;

  @Column(name = "joining_date", nullable = false, updatable = false)
  @Getter
  @Setter
  private Date joiningDate;

  @Column(name = "exit_date", nullable = true)
  @Getter
  @Setter
  private Date exitDate;

  @Column(name = "address", nullable = true, length = 300)
  @Getter
  @Setter
  private String address;

  @Column(name = "contact", nullable = false, length = 100)
  @Getter
  @Setter
  private String contact;

  @Column(name = "designation", nullable = false, length = 200)
  @Getter
  @Setter
  private String designation;

  @CreationTimestamp
  @Column(name = "created_at", nullable = false, updatable = false)
  @Getter
  @Setter
  private Date createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at", nullable = false)
  @Getter
  @Setter
  private Date updatedAt;

  public Employee()
  {

  }

  public Employee(Employee employee)
  {
    this.setId(employee.getId());
    this.setGuid(employee.getGuid());
    this.setEmployeeId(employee.getEmployeeId());
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
    return employeeId.equals(employee.employeeId) &&
        firstName.equals(employee.firstName) &&
        middleName.equals(employee.middleName) &&
        lastName.equals(employee.lastName) &&
        email.equals(employee.email);
  }

  @Override
  public int hashCode()
  {
    return Objects.hash(employeeId, firstName, middleName, lastName, email);
  }

  /**
   *
   */
  private static final long serialVersionUID = 1L;
}
