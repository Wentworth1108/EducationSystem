package cn.gcs.student.service;

import java.io.File;
import java.util.List;

import javax.servlet.ServletOutputStream;

import cn.gcs.core.service.BaseService;
import cn.gcs.student.entity.Student;
import cn.gcs.user.entity.User;

public interface StudentService extends BaseService<Student>{
	
	
	//导入Excel
	public void importExcel(File stuExcel, String stuExcelFileName);
	
	//导出Excel
	public void exportExcel(ServletOutputStream outputStream);
	
	
	//根据学号查找学生
	public Student getStuByStudent_id(String student_id);
	
	//根据学生学号查找学生列表
	public List<Student> findUserByStuidAndId(String student_id, Integer id);
	
	
}
