package cn.gcs.classinfo.service;

import java.util.List;

import cn.gcs.classinfo.entity.ClassInfo;
import cn.gcs.core.service.BaseService;
import cn.gcs.user.entity.User;

public interface ClassInfoService extends BaseService<ClassInfo> {

	// 保存班级及其对应的课程
	void saveClassInfoAndCourse(ClassInfo classInfo, Integer... courseIds);
	
	// 更新班级及其对应的课程
	void updateClassInfoAndCourse(ClassInfo classInfo, Integer... courseIds);

	// 保存班级、班主任及其对应的课程
	void saveClassInfoAndCourseAndUser(ClassInfo classInfo, Integer userId, Integer... courseIds);

	// 更新班级、班主任及对应的课程
	void updateClassInfoAndCourseAndUser(ClassInfo classInfo, Integer userId, Integer... courseIds);

	// 查询班级名
	List<String> findclassNames();
	
	//通过班级名查找班级
	ClassInfo getClassByName(String name);

}
