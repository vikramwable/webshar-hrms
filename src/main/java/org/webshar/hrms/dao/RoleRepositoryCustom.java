package org.webshar.hrms.dao;

import java.util.List;
import org.webshar.hrms.models.db.Role;

public interface RoleRepositoryCustom
{
    List<Role> getRoleNameLike(String name);
}
