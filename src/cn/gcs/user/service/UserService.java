package cn.gcs.user.service;

import java.util.List;

import cn.gcs.core.service.BaseService;
import cn.gcs.user.entity.User;
import cn.gcs.user.entity.UserRole;

public interface UserService extends BaseService<User> {

	// 根据用户账号和用户id查询用户
	public List<User> findUserByAccountAndId(String account, Integer id);

	// 保存用户及其对应的角色
	public void saveUserAndRole(User user, Integer... roleIds);

	// 更新用户及其对应的角色
	public void updateUserAndRole(User user, Integer... roleIds);

	// 根据用户id查询用户角色
	public List<UserRole> getUserRolesByUserId(Integer id);

	// 根据用户账号和密码查询用户
	public User findUserByAccountAndPassword(String account, String password);

	// 根据角色名查找用户
	public List<User> findUserByRoleName(String roleName);

	//根据用户的名字查找唯一用户
	public User getUserByName(String name);

}
