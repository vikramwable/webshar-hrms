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
@Table(name = "resource_permission", uniqueConstraints = @UniqueConstraint(columnNames = {"id"}))
public class ResourcePermission implements Serializable
{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false, unique = true, length = 11)
  @Getter
  @Setter
  private Long id;

  @Column(name = "resource_id", nullable = false, unique = true, length = 11)
  @Getter
  @Setter
  private Long resourceId;

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

  public ResourcePermission()
  {

  }

  public ResourcePermission(ResourcePermission resourcePermission)
  {
    this.setId(resourcePermission.getId());
    this.setResourceId(resourcePermission.getResourceId());
    this.setPermissionId(resourcePermission.getPermissionId());
    this.setCreatedAt(resourcePermission.getCreatedAt());
  }

  @Override
  public String toString()
  {
    return "ResourcePermission{" +
        "resourceId=" + resourceId +
        ", permissionId=" + permissionId +
        ", createdAt=" + createdAt +
        ", updatedAt=" + updatedAt +
        '}';
  }
}
