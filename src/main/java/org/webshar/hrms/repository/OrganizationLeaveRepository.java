package org.webshar.hrms.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.webshar.hrms.model.db.OrganizationLeave;

@Repository
public interface OrganizationLeaveRepository extends JpaRepository<OrganizationLeave, Long> {

  List<OrganizationLeave> findByOrganizationId(@Param("org_id") Long organizationId);

  List<OrganizationLeave> findByOrganizationIdAndLeaveTypeId(@Param("org_id") Long organizationId,
      @Param("leave_type_id") Long leaveTypeId);

}