package org.webshar.hrms.dao.impl;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.webshar.hrms.dao.RoleRepository;
import org.webshar.hrms.dao.RoleRepositoryCustom;
import org.webshar.hrms.models.db.Role;

@Repository
public class RoleDaoImpl implements RoleRepositoryCustom
{

  @PersistenceContext
  EntityManager entityManager;

  @Override
  public List<Role> getRoleNameLike(String name)
  {
    Query query = entityManager.createQuery("SELECT name FROM Role WHERE name LIKE ?",Role.class);
    query.setParameter(1,name);
    return  query.getResultList();
  }
}
