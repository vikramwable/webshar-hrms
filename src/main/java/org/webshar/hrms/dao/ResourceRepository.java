package org.webshar.hrms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.webshar.hrms.models.db.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Long>
{

}
