package cn.gcs.student.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import cn.gcs.classinfo.entity.ClassInfo;
import cn.gcs.score.entity.Score;
import cn.gcs.user.entity.User;
import cn.gcs.violate.entity.Violate;

@SuppressWarnings("serial")
@Entity
public class Student implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer studentId;

	//在校学号
	private String student_id;

	public String getStudent_id() {
		return student_id;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}

	//在校班级
	private String school_class;

	//就业状态
	private String employment_status;

	//个人联系方式
	private String student_phone;

	//家庭联系方式
	private String home_phone;

	//毕业院校
	private String graduated_university;

	//毕业时间
	private Date graduated_time;

	//在校专业
	private String school_major;

	//教育背景
	private String educational_background;

	//英语级别
	private String english_class;

	//专业方向
	private String major;

	//生源地
	private String place;

	//家庭住址
	private String address;

	//政治面貌
	private String political;

	//入学时间
	private Date admission_time;

	//结束时间
	private Date end_time;

	//阶段
	private String stage;

	//日常得分
	private double dailyScore;

	@OneToMany(mappedBy = "student")
	private List<Violate> attendances = new ArrayList<Violate>();

	@ManyToOne(targetEntity = User.class)
	private User user;

	@ManyToOne(targetEntity = ClassInfo.class)
	@JoinColumn(name = "class_id")
	private ClassInfo classInfo;

	// 成绩
	@OneToMany(mappedBy = "student")
	private List<Score> scores;

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getSchool_class() {
		return school_class;
	}

	public void setSchool_class(String school_class) {
		this.school_class = school_class;
	}

	public String getEmployment_status() {
		return employment_status;
	}

	public void setEmployment_status(String employment_status) {
		this.employment_status = employment_status;
	}

	public String getStudent_phone() {
		return student_phone;
	}

	public void setStudent_phone(String student_phone) {
		this.student_phone = student_phone;
	}

	public String getHome_phone() {
		return home_phone;
	}

	public void setHome_phone(String home_phone) {
		this.home_phone = home_phone;
	}

	public String getGraduated_university() {
		return graduated_university;
	}

	public void setGraduated_university(String graduated_university) {
		this.graduated_university = graduated_university;
	}

	public Date getGraduated_time() {
		return graduated_time;
	}

	public void setGraduated_time(Date graduated_time) {
		this.graduated_time = graduated_time;
	}

	public String getSchool_major() {
		return school_major;
	}

	public void setSchool_major(String school_major) {
		this.school_major = school_major;
	}

	public String getEnglish_class() {
		return english_class;
	}

	public void setEnglish_class(String english_class) {
		this.english_class = english_class;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPolitical() {
		return political;
	}

	public void setPolitical(String political) {
		this.political = political;
	}

	public Date getAdmission_time() {
		return admission_time;
	}

	public void setAdmission_time(Date admission_time) {
		this.admission_time = admission_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ClassInfo getClassInfo() {
		return classInfo;
	}

	public void setClassInfo(ClassInfo classInfo) {
		this.classInfo = classInfo;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public double getDailyScore() {
		return dailyScore;
	}

	public void setDailyScore(double dailyScore) {
		this.dailyScore = dailyScore;
	}

	public List<Violate> getAttendances() {
		return attendances;
	}

	public void setAttendances(List<Violate> attendances) {
		this.attendances = attendances;
	}

	public List<Score> getScores() {
		return scores;
	}

	public void setScores(List<Score> scores) {
		this.scores = scores;
	}

	public String getEducational_background() {
		return educational_background;
	}

	public void setEducational_background(String educational_background) {
		this.educational_background = educational_background;
	}

}
