package org.webshar.hrms.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.webshar.hrms.model.db.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

  List<Organization> findByName(@Param("name") String name);
}
