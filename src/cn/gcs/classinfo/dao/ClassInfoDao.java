package cn.gcs.classinfo.dao;

import java.util.List;

import cn.gcs.classinfo.entity.ClassInfo;
import cn.gcs.classinfo.entity.Elective;
import cn.gcs.core.dao.BaseDao;
import cn.gcs.user.entity.User;

public interface ClassInfoDao extends BaseDao<ClassInfo> {

	// 保存选课信息
	void saveElective(Elective elective);

	// 保存班主任
	void saveUser(User user);

	// 删除班级对应的课程
	void deleteClassByClassIds(Integer classId);

	// 查询班级名
	List<String> findclassNames();

	//通过班级和教室查找班级
	ClassInfo findClassByNameAndRoom(String className, String classRoom);
	
	//通过班级名称查找班级
	ClassInfo getClassByName(String name);

}
