package cn.gcs.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gcs.core.service.impl.BaseServiceImpl;
import cn.gcs.role.entity.Role;
import cn.gcs.user.dao.UserDao;
import cn.gcs.user.entity.User;
import cn.gcs.user.entity.UserRole;
import cn.gcs.user.entity.UserRoleId;
import cn.gcs.user.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User> implements
		UserService {

	private UserDao userDao;

	@Resource
	public void setUserDao(UserDao userDao) {
		super.setBaseDao(userDao);
		this.userDao = userDao;
	}

	@Override
	public List<User> findUserByAccountAndId(String account, Integer id) {

		return userDao.findUserByAccountAndId(account, id);
	}

	@Override
	public void saveUserAndRole(User user, Integer... roleIds) {
		//1.保存用户
		save(user);
		//2.保存用户对应的角色
		if (roleIds != null) {
			for (Integer roleId : roleIds) {
				userDao.saveUserRole(new UserRole(new UserRoleId(new Role(
						roleId), user)));
			}
		}
	}

	@Override
	public void updateUserAndRole(User user, Integer... roleIds) {
		//1.根据用户删除该用户的所有角色
		userDao.deleteUserRoleByUserId(user.getId());
		//2.更新用户
		update(user);
		//3.保存用户对应的角色
		if (roleIds != null) {
			for (Integer roleId : roleIds) {
				userDao.saveUserRole(new UserRole(new UserRoleId(new Role(
						roleId), user)));
			}
		}
	}

	@Override
	public List<UserRole> getUserRolesByUserId(Integer id) {
		return userDao.getUserRolesByUserId(id);
	}

	@Override
	public List<User> findUserByRoleName(String roleName) {
		return userDao.findUserByRoleName(roleName);
	}

	@Override
	public User getUserByName(String name) {
		return userDao.getUserByName(name);
	}

	@Override
	public User findUserByAccountAndPassword(String account, String password) {
		return userDao.findUserByAccountAndPassword(account, password);
	}
}
