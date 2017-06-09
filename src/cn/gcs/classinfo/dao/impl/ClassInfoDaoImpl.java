package cn.gcs.classinfo.dao.impl;

import java.util.List;

import org.hibernate.Query;

import cn.gcs.classinfo.dao.ClassInfoDao;
import cn.gcs.classinfo.entity.ClassInfo;
import cn.gcs.classinfo.entity.Elective;
import cn.gcs.core.dao.impl.BaseDaoImpl;
import cn.gcs.user.entity.User;

public class ClassInfoDaoImpl extends BaseDaoImpl<ClassInfo> implements ClassInfoDao {

	@Override
	public void saveElective(Elective elective) {
		getHibernateTemplate().save(elective);
	}

	@Override
	public void saveUser(User user) {
		getHibernateTemplate().save(user);
	}

	@Override
	public void deleteClassByClassIds(Integer classId) {
		Query query = getSessionFactory().getCurrentSession().createQuery("DELETE FROM Elective WHERE id.classId = ?");
		query.setParameter(0, classId);
		query.executeUpdate();
	}

	@Override
	public List<String> findclassNames() {
		Query query = getSessionFactory().getCurrentSession().createQuery("SELECT name FROM ClassInfo");
		
		return query.list();
	}

	@Override
	public ClassInfo findClassByNameAndRoom(String className, String classRoom) {
		Query query = getSessionFactory().getCurrentSession().createQuery("FROM ClassInfo WHERE name=? AND classroom=?");
		query.setParameter(0, className);
		query.setParameter(1, classRoom);
		return (ClassInfo) query.uniqueResult();
	}

	@Override
	public ClassInfo getClassByName(String name) {
		Query query = currentSession().createQuery("FROM ClassInfo WHERE name=?");
		query.setParameter(0, name);
		return (ClassInfo) query.uniqueResult();
	}

}
