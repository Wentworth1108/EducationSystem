package cn.gcs.student.dao.impl;

import java.util.List;

import org.hibernate.Query;

import cn.gcs.core.dao.impl.BaseDaoImpl;
import cn.gcs.student.dao.StudentDao;
import cn.gcs.student.entity.Student;

public class StudentDaoImpl extends BaseDaoImpl<Student> implements StudentDao {

	@Override
	public Student getStuByStudent_id(String student_id) {
		Query query = currentSession().createQuery(
				"FROM Student WHERE student_id=?");
		query.setParameter(0, student_id);
		return (Student) query.uniqueResult();
	}

	@Override
	public List<Student> findUserByStuidAndId(String student_id, Integer id) {
		String hql = "FROM Student WHERE student_id = ?";
		if (id != null) {
			hql += " AND studentId != ?";
		}
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter(0, student_id);
		if (id != null) {
			query.setParameter(1, id);
		}
		return query.list();
	}

	@Override
	public Student getById(int studentid) {
		return currentSession().get(Student.class, studentid);
	}

}
