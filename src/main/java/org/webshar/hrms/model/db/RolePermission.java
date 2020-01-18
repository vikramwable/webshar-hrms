package org.webshar.hrms.model.db;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "role_permission", uniqueConstraints = @UniqueConstraint(columnNames = {"id"}))
public class RolePermission implements Serializable
{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true, length = 11)
  @Getter
  @Setter
  private Long id;

  @Column(name = "role_id", nullable = false, unique = true, length = 11)
  @Getter
  @Setter
  private Long roleId;

  @Column(name = "permission_id", nullable = false, unique = true, length = 11)
  @Getter
  @Setter
  private Long permissionId;

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

  public RolePermission(){

  }

  public  RolePermission(RolePermission rolePermission){
    this.setId(rolePermission.getId());
    this.setRoleId(rolePermission.getRoleId());
    this.setPermissionId(rolePermission.getPermissionId());
    this.setCreatedAt(rolePermission.getCreatedAt());
  }
  @Override
  public String toString()
  {
    return "RolePermission{" +
        ", roleId=" + roleId +
        ", permissionId=" + permissionId +
        ", createdAt=" + createdAt +
        ", updatedAt=" + updatedAt +
        '}';
  }
}
