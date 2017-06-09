package cn.gcs.role.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gcs.core.service.impl.BaseServiceImpl;
import cn.gcs.role.dao.RoleDao;
import cn.gcs.role.entity.Role;
import cn.gcs.role.service.RoleService;
		
@Service("roleService")
@Transactional
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

	private RoleDao roleDao;
	
	@Resource
	public void setRoleDao(RoleDao roleDao) {
		super.setBaseDao(roleDao);
		this.roleDao = roleDao;
	}
	
	@Override
	public void update(Role role) {
		// 1.删除该角色对应的所有权限
		roleDao.deleteRolePrivilegeByRoleId(role.getRoleId());
		//2、更新角色及其权限
		roleDao.update(role);
	}
}
