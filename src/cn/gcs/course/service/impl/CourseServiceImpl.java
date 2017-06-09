package cn.gcs.course.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gcs.core.service.impl.BaseServiceImpl;
import cn.gcs.course.dao.CourseDao;
import cn.gcs.course.entity.Course;
import cn.gcs.course.entity.CourseTeacher;
import cn.gcs.course.entity.CourseTeacherId;
import cn.gcs.course.service.CourseService;
import cn.gcs.user.entity.User;

@Service("courseService")
@Transactional
public class CourseServiceImpl extends BaseServiceImpl<Course> implements CourseService {

	private CourseDao courseDao;
	
	@Resource
	public void setCourseDao(CourseDao courseDao) {
		super.setBaseDao(courseDao);
		this.courseDao = courseDao;
	}

	@Override
	public void saveCourseAndTeacher(Course course, Integer... userIds) {
		//1.保存用户
		save(course);
		//2.保存用户对应的角色
		if (userIds != null) {
			for (Integer userId : userIds) {
				courseDao.saveCourseTeacher(new CourseTeacher(new CourseTeacherId(course ,new User(userId))));
			}
		}
	}

	@Override
	public void upadateCourseAndTeacher(Course course, Integer... userIds) {
		//1.根据课程删除该课程的所有老师
		courseDao.deleteCourseTeacherByCourseId(course.getCourseId());
		//2.更新课程
		update(course);
		//3.保存课程对应的任课老师
		if (userIds != null) {
			for (Integer userId : userIds) {
				courseDao.saveCourseTeacher(new CourseTeacher(new CourseTeacherId(course ,new User(userId))));
			}
		}
	}

	@Override
	public List<String> findCourseNames() {
		return courseDao.findCourseNames();
	}

}
