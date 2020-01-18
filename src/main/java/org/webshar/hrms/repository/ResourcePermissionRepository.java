package org.webshar.hrms.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.webshar.hrms.model.db.ResourcePermission;

public interface ResourcePermissionRepository extends JpaRepository<ResourcePermission, Long>
{

  List<ResourcePermission> findByResourceIdAndPermissionId(@Param("resource_id") Long resourceId,
      @Param("permission_id") Long permissionId);
}
