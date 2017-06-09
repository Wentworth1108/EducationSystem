package cn.gcs.role.dao.impl;

import org.hibernate.Query;

import cn.gcs.core.dao.impl.BaseDaoImpl;
import cn.gcs.role.dao.RoleDao;
import cn.gcs.role.entity.Role;

public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

	@Override
	public void deleteRolePrivilegeByRoleId(Integer roleId) {
		Query query = currentSession().createQuery("DELETE FROM RolePrivilege WHERE id.role.roleId = ?");
		query.setParameter(0, roleId);
		query.executeUpdate();
	}

}
