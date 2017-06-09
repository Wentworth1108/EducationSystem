package cn.gcs.role.dao;

import cn.gcs.core.dao.BaseDao;
import cn.gcs.role.entity.Role;

public interface RoleDao extends BaseDao<Role> {

	void deleteRolePrivilegeByRoleId(Integer roleId);

}
