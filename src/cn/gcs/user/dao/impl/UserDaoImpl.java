package cn.gcs.user.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;

import cn.gcs.core.dao.impl.BaseDaoImpl;
import cn.gcs.user.dao.UserDao;
import cn.gcs.user.entity.User;
import cn.gcs.user.entity.UserRole;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public List<User> findUserByAccountAndId(String account, Integer id) {
		String hql = "FROM User WHERE account = ?";
		if (id != null) {
			hql += " AND id != ?";
		}
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter(0, account);
		if (id != null) {
			query.setParameter(1, id);
		}
		return query.list();
	}

	@Override
	public void saveUserRole(UserRole userRole) {
		getHibernateTemplate().save(userRole);
	}

	@Override
	public void deleteUserRoleByUserId(Serializable id) {
		// delete from user_role where user_id = id;
		Query query = getSessionFactory().getCurrentSession().createQuery(
				"DELETE FROM UserRole WHERE id.user.id = ?");
		query.setParameter(0, id);
		query.executeUpdate();
	}

	@Override
	public List<UserRole> getUserRolesByUserId(Integer id) {
		Query query = getSessionFactory().getCurrentSession().createQuery(
				"FROM UserRole WHERE id.user.id = ?");
		query.setParameter(0, id);

		return query.list();
	}

	@Override
	public User findUserByAccountAndPassword(String account, String password) {
		Query query = getSessionFactory().getCurrentSession().createQuery(
				"FROM User WHERE account = ? AND password = ?");
		query.setParameter(0, account);
		query.setParameter(1, password);

		return (User) query.uniqueResult();
	}

	@Override
	public List<User> findUserByRoleName(String roleName) {
		Query query = getSessionFactory().getCurrentSession().createQuery(
				"FROM UserRole WHERE id.role.name = ?");
		query.setParameter(0, roleName);

		return query.list();
	}

	@Override
	public User findUserByAccountAndName(String student_id, String name) {
		Query query = currentSession().createQuery(
				"FROM User WHERE account=? AND name=?");
		query.setParameter(0, student_id);
		query.setParameter(1, name);
		return (User) query.uniqueResult();
	}

	@Override
	public User getUserByName(String name) {
		Query query = currentSession().createQuery("FROM User WHERE name=?");
		query.setParameter(0, name);
		return (User) query.uniqueResult();
	}

	@Override
	public String getUserRoleById(int userid) {
		return (String) currentSession()
				.createSQLQuery(
						"SELECT role.name FROM role JOIN user_role WHERE user_role.user_id = :id AND user_role.role_id = role.role_id")
				.setParameter("id", userid).list().get(0);

	}
}
