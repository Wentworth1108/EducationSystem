package cn.gcs.course.service;

import java.util.List;

import cn.gcs.core.service.BaseService;
import cn.gcs.course.entity.Course;

public interface CourseService extends BaseService<Course>{
	
	// 保存课程及其对应的用户
	void saveCourseAndTeacher(Course course, Integer... userIds);
	
	// 更新课程及其对应的用户
	void upadateCourseAndTeacher(Course course, Integer... userIds);

	// 查找所有班级名
	List<String> findCourseNames();


}
