package org.webshar.hrms.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.webshar.hrms.dao.RoleDao;
import org.webshar.hrms.models.db.Role;

@Repository
public class RoleDaoImpl implements RoleDao
{

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public Role findById(Long id)
  {
    return entityManager.find(Role.class, id);
  }

  @Override
  public Role save(Role persisted)
  {
    return null;
  }

  @Override
  public Role update(Role updated)
  {
    return null;
  }

  @Override
  public List<Role> findAll()
  {
    return null;
  }

  @Override
  public void delete(Role deleted)
  {
    Object managed = entityManager.merge(deleted);
    entityManager.remove(managed);
  }
}
