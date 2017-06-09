package cn.gcs.course.dao;

import java.util.List;

import cn.gcs.core.dao.BaseDao;
import cn.gcs.course.entity.Course;
import cn.gcs.course.entity.CourseTeacher;

public interface CourseDao extends BaseDao<Course> {

	// 保存任课老师
	void saveCourseTeacher(CourseTeacher courseTeacher);

	// 删除课程老师根据该课程id
	void deleteCourseTeacherByCourseId(Integer courseId);

	// 查找班级名
	List<String> findCourseNames();

}
