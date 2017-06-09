package cn.gcs.student.dao;

import java.util.List;

import cn.gcs.core.dao.BaseDao;
import cn.gcs.student.entity.Student;

public interface StudentDao extends BaseDao<Student> {

	//根据学号查找学生
	Student getStuByStudent_id(String student_id);

	//根据学生学号查找学生列表
	List<Student> findUserByStuidAndId(String student_id, Integer id);

	Student getById(int studentid);

}
