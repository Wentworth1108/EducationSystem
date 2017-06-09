package cn.gcs.course.dao.impl;

import java.util.List;

import org.hibernate.Query;

import cn.gcs.core.dao.impl.BaseDaoImpl;
import cn.gcs.course.dao.CourseDao;
import cn.gcs.course.entity.Course;
import cn.gcs.course.entity.CourseTeacher;

public class CourseDaoImpl extends BaseDaoImpl<Course> implements CourseDao {

	@Override
	public void saveCourseTeacher(CourseTeacher courseTeacher) {
		getHibernateTemplate().save(courseTeacher);
	}

	@Override
	public void deleteCourseTeacherByCourseId(Integer courseId) {
		Query query = getSessionFactory().getCurrentSession().createQuery("DELETE FROM CourseTeacher WHERE id.course.courseId = ?");
		query.setParameter(0, courseId);
		query.executeUpdate();
	}

	@Override
	public List<String> findCourseNames() {
		Query query = getSessionFactory().getCurrentSession().createQuery("SELECT name FROM Course");
		return query.list();
	}

}
