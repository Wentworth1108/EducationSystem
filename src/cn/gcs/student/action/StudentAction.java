package cn.gcs.student.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import cn.gcs.classinfo.service.ClassInfoService;
import cn.gcs.core.action.BaseAction;
import cn.gcs.core.util.QueryHelper;
import cn.gcs.score.service.ScoreService;
import cn.gcs.student.entity.Student;
import cn.gcs.student.service.StudentService;
import cn.gcs.student.vo.StudentItem;
import cn.gcs.user.entity.User;
import cn.gcs.user.service.UserService;

import com.opensymphony.xwork2.ActionContext;

public class StudentAction extends BaseAction {

	@Resource
	private StudentService studentService;

	@Resource
	private UserService userService;

	@Resource
	private ClassInfoService classInfoService;

	@Resource
	private ScoreService scoreService;

	private User user;
	private Student student;

	private List<Student> stuList;

	private Integer classId;

	private Map<String, Object> map = new HashMap<>();

	private File stuExcel;
	private String stuExcelContentType;
	private String stuExcelFileName;

	public String stuList() throws Exception {
		QueryHelper queryHelper = new QueryHelper(Student.class, "s");
		try {
			if (student != null) {
				if (StringUtils.isNotBlank(student.getStudent_id())) {
					queryHelper.addCondition("s.student_id like ?", "%"
							+ student.getStudent_id() + "%");
				}
			}
			pageResult = studentService.getPageResult(queryHelper, getPage(),
					getRows());
			int total = (int) pageResult.getTotalCount();
			List<Student> list = pageResult.getItems();
			List<StudentItem> result = new ArrayList<StudentItem>();
			for (Student s : list) {
				StudentItem temp = new StudentItem();
				temp.setStudentId(s.getStudentId());
				temp.setStudent_id(s.getStudent_id());
				temp.setName(s.getUser().getName());
				temp.setGender(s.getUser().isGender());
				temp.setBirthday(s.getUser().getBirthday());
				temp.setIdNumber(s.getUser().getIdNumber());
				temp.setEmail(s.getUser().getEmail());
				temp.setMobile(s.getUser().getMobile());
				temp.setAddress(s.getAddress());
				temp.setSchool_class(s.getSchool_class());
				temp.setEmployment_status(s.getEmployment_status());
				temp.setStage(s.getStage());
				temp.setStudent_phone(s.getStudent_phone());
				temp.setHome_phone(s.getHome_phone());
				temp.setGraduated_university(s.getGraduated_university());
				temp.setGraduated_time(s.getGraduated_time());
				temp.setSchool_major(s.getSchool_major());
				temp.setPlace(s.getPlace());
				temp.setPolitical(s.getPolitical());
				temp.setAdmission_time(s.getAdmission_time());
				temp.setEnd_time(s.getEnd_time());
				temp.setClassName(s.getClassInfo().getName());
				temp.setClassRoom(s.getClassInfo().getClassroom());
				temp.setEnglish_class(s.getEnglish_class());
				temp.setMajor(s.getMajor());
				temp.setDailyScore(s.getDailyScore());
				temp.setEducational_background(s.getEducational_background());
				temp.setScores(s.getScores());
				result.add(temp);
			}
			map.put("total", total);
			map.put("rows", result);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "stuList";
	}

	public String infoList() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		Integer id = (Integer) session.get("id");
		QueryHelper queryHelper = new QueryHelper(Student.class, "s");
		try {
			queryHelper.addCondition("s.user.id=?", id);
			pageResult = studentService.getPageResult(queryHelper, getPage(),
					getRows());
			int total = (int) pageResult.getTotalCount();
			List<Student> list = pageResult.getItems();
			List<StudentItem> result = new ArrayList<StudentItem>();
			for (Student s : list) {
				StudentItem temp = new StudentItem();
				temp.setStudentId(s.getStudentId());
				temp.setStudent_id(s.getStudent_id());
				temp.setName(s.getUser().getName());
				temp.setGender(s.getUser().isGender());
				temp.setBirthday(s.getUser().getBirthday());
				temp.setIdNumber(s.getUser().getIdNumber());
				temp.setEmail(s.getUser().getEmail());
				temp.setMobile(s.getUser().getMobile());
				temp.setAddress(s.getAddress());
				temp.setSchool_class(s.getSchool_class());
				temp.setEmployment_status(s.getEmployment_status());
				temp.setStage(s.getStage());
				temp.setStudent_phone(s.getStudent_phone());
				temp.setHome_phone(s.getHome_phone());
				temp.setGraduated_university(s.getGraduated_university());
				temp.setGraduated_time(s.getGraduated_time());
				temp.setSchool_major(s.getSchool_major());
				temp.setPlace(s.getPlace());
				temp.setPolitical(s.getPolitical());
				temp.setAdmission_time(s.getAdmission_time());
				temp.setEnd_time(s.getEnd_time());
				temp.setClassName(s.getClassInfo().getName());
				temp.setClassRoom(s.getClassInfo().getClassroom());
				temp.setEnglish_class(s.getEnglish_class());
				temp.setMajor(s.getMajor());
				temp.setDailyScore(s.getDailyScore());
				temp.setEducational_background(s.getEducational_background());
				temp.setScores(s.getScores());
				result.add(temp);
			}
			map.put("total", total);
			map.put("rows", result);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "infoList";
	}

	//导出用户列表
	public String exportExcel() {
		try {
			//2.导出
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("application/x-execl");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ new String("学生列表.xls".getBytes(), "ISO-8859-1"));
			ServletOutputStream outputStream = response.getOutputStream();
			studentService.exportExcel(outputStream);

			if (outputStream != null) {
				outputStream.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	//导入
	public String importExcel() {
		//1. 获取Excel文件
		if (stuExcel != null) {
			// 是否是Excel
			if (stuExcelFileName.matches("^.+\\.(?i)((xls)|(xlsx))$")) {
				//导入
				studentService.importExcel(stuExcel, stuExcelFileName);
			}
		}
		return SUCCESS;
	}

	//个人修改学生信息
	public String infoEdit() {
		try {
			if (student != null) {
				//用户
				List<User> userList = userService.findUserByAccountAndId(
						student.getStudent_id(), student.getUser().getId());
				User user = userList.get(0);
				user.setAccount(student.getStudent_id());
				user.setName(student.getUser().getName());
				user.setGender(student.getUser().isGender());
				user.setBirthday(student.getUser().getBirthday());
				user.setIdNumber(student.getUser().getIdNumber());
				user.setEmail(student.getUser().getEmail());
				user.setMobile(student.getUser().getMobile());

				userService.update(user);
				//学生
				Student stu = studentService.findObjectById(student
						.getStudentId());
				stu.setAddress(student.getAddress());
				stu.setGraduated_university(student.getGraduated_university());
				stu.setEducational_background(student
						.getEducational_background());
				stu.setEnglish_class(student.getEnglish_class());
				stu.setMajor(student.getMajor());
				studentService.update(stu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "infoEdit";

	}

	//管理员修改学生信息 
	public String listEdit() {
		try {
			if (student != null) {
				//用户
				List<User> userList = userService.findUserByAccountAndId(
						student.getStudent_id(), student.getUser().getId());
				User user = userList.get(0);
				user.setAccount(student.getStudent_id());
				user.setName(student.getUser().getName());
				user.setGender(student.getUser().isGender());
				user.setBirthday(student.getUser().getBirthday());
				user.setIdNumber(student.getUser().getIdNumber());
				user.setEmail(student.getUser().getEmail());
				user.setMobile(student.getUser().getMobile());

				userService.update(user);
				//学生
				Student stu = studentService.findObjectById(student
						.getStudentId());
				stu.setAddress(student.getAddress());
				stu.setGraduated_university(student.getGraduated_university());
				stu.setEducational_background(student
						.getEducational_background());
				stu.setEnglish_class(student.getEnglish_class());
				stu.setMajor(student.getMajor());
				studentService.update(stu);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "listEdit";

	}

	public List<Student> getStuList() {

		return stuList;
	}

	public void setStuList(List<Student> stuList) {
		this.stuList = stuList;
	}

	public File getStuExcel() {
		return stuExcel;
	}

	public void setStuExcel(File stuExcel) {
		this.stuExcel = stuExcel;
	}

	public String getStuExcelContentType() {
		return stuExcelContentType;
	}

	public void setStuExcelContentType(String stuExcelContentType) {
		this.stuExcelContentType = stuExcelContentType;
	}

	public String getStuExcelFileName() {
		return stuExcelFileName;
	}

	public void setStuExcelFileName(String stuExcelFileName) {
		this.stuExcelFileName = stuExcelFileName;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public StudentService getStudentService() {
		return studentService;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

}
