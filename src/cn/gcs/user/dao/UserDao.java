package cn.gcs.user.dao;

import java.io.Serializable;
import java.util.List;

import cn.gcs.core.dao.BaseDao;
import cn.gcs.user.entity.User;
import cn.gcs.user.entity.UserRole;

public interface UserDao extends BaseDao<User> {

	/**
	 * 根据账号和用户id查询用户
	 * @param id 用户id
	 * @param account 用户账号
	 * @return 用户列表
	 */
	public List<User> findUserByAccountAndId(String account, Integer id);

	// 保存用户角色
	public void saveUserRole(UserRole userRole);

	// 删除用户角色根据该用户id
	public void deleteUserRoleByUserId(Serializable id);

	// 根据用户id获取该用户对应的所有用户角色
	public List<UserRole> getUserRolesByUserId(Integer id);

	// 根据用户的账号、密码查询用户
	public User findUserByAccountAndPassword(String account, String password);

	// 根据角色名查找角色
	public List<User> findUserByRoleName(String roleName);

	//根据角色的账号和名字查找唯一用户
	public User findUserByAccountAndName(String student_id, String name);

	//根据角色名字查找唯一用户
	public User getUserByName(String name);

	public String getUserRoleById(int userid);

}
