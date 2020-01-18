package org.webshar.hrms.repository;

    import java.util.List;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.repository.query.Param;
    import org.webshar.hrms.model.db.RolePermission;

public interface RolePermissionRepository extends JpaRepository<RolePermission, Long>
{

 List<RolePermission> findByRoleIdAndPermissionId(@Param("role_id") Long roleId,
     @Param("permission_id") Long permissionId);
}
