package org.webshar.hrms.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends Repository<T, ID>
{
  public T findById(ID id);

  public T save(T persisted);

  public T update(T updated);

  public void delete(T deleted);

  public List<T> findAll();
}