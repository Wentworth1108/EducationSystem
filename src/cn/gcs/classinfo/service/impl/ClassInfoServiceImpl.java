package cn.gcs.classinfo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gcs.classinfo.dao.ClassInfoDao;
import cn.gcs.classinfo.entity.ClassInfo;
import cn.gcs.classinfo.entity.Elective;
import cn.gcs.classinfo.entity.ElectiveId;
import cn.gcs.classinfo.service.ClassInfoService;
import cn.gcs.core.service.impl.BaseServiceImpl;
import cn.gcs.course.entity.Course;
import cn.gcs.course.entity.CourseTeacher;
import cn.gcs.course.entity.CourseTeacherId;
import cn.gcs.user.entity.User;

@Service("classInfoService")
@Transactional
public class ClassInfoServiceImpl extends BaseServiceImpl<ClassInfo> implements ClassInfoService {

	private ClassInfoDao classInfoDao;
	
	@Resource
	public void setClassInfoDao(ClassInfoDao classInfoDao) {
		super.setBaseDao(classInfoDao);
		this.classInfoDao = classInfoDao;
	}

	@Override
	public void saveClassInfoAndCourse(ClassInfo classInfo, Integer... courseIds) {
		//1.保存班级
		save(classInfo);
		//2.保存班级及其课程
		if (courseIds != null) {
			for (Integer courseId : courseIds) {
				classInfoDao.saveElective(new Elective(new ElectiveId(new Course(courseId), classInfo.getClassId())));
			}
		}
	}

	@Override
	public void updateClassInfoAndCourse(ClassInfo classInfo, Integer... courseIds) {
		
	}

	@Override
	public void saveClassInfoAndCourseAndUser(ClassInfo classInfo, Integer userId, Integer... courseIds) {
		//1.保存班级
		save(classInfo);
		//2.保存班主任
		if (userId != null) {
			classInfo.setUser(new User(userId));
		}
		//3保存班级及其课程
		if (courseIds != null) {
			for (Integer courseId : courseIds) {
				classInfoDao.saveElective(new Elective(new ElectiveId(new Course(courseId), classInfo.getClassId())));
			}
		}
	}

	@Override
	public void updateClassInfoAndCourseAndUser(ClassInfo classInfo, Integer userId, Integer... courseIds) {
		// 1.根据班级删除所有课程
		classInfoDao.deleteClassByClassIds(classInfo.getClassId());
		// 2.更新班级
		classInfo.setUser(new User(userId));
		update(classInfo);
		//3.保存班级对应的课程
		if (courseIds != null) {
			for (Integer courseId : courseIds) {
				classInfoDao.saveElective(new Elective(new ElectiveId(new Course(courseId), classInfo.getClassId())));
			}
		}
	}

	@Override
	public List<String> findclassNames() {
		return classInfoDao.findclassNames();
	}

	@Override
	public ClassInfo getClassByName(String name) {
		return classInfoDao.getClassByName(name);
	}
}
