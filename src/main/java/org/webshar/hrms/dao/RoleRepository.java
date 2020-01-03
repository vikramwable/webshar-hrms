package org.webshar.hrms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.webshar.hrms.models.db.Role;

public interface RoleRepository extends JpaRepository<Role, Long>
{
}