package cn.gcs.student.vo;

import java.util.Date;
import java.util.List;

import cn.gcs.score.entity.Score;

public class StudentItem {
	private Integer studentId;
	private String name;
	private boolean gender;
	private Date birthday;
	private String idNumber;
	private String email;
	private String mobile;
	private String student_id;
	private String address;
	private Date admission_time;
	private String employment_status;
	private Date end_time;
	private String english_class;
	private Date graduated_time;
	private String graduated_university;
	private String home_phone;
	private String major;
	private String place;
	private String political;
	private String school_class;
	private String school_major;
	private String student_phone;
	private String className;
	private String classRoom;
	private String stage;
	private double dailyScore;
	private String educational_background;
	
	private List<Score> scores;
	
	
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public boolean isGender() {
		return gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getAdmission_time() {
		return admission_time;
	}
	public void setAdmission_time(Date admission_time) {
		this.admission_time = admission_time;
	}
	public String getEmployment_status() {
		return employment_status;
	}
	public void setEmployment_status(String employment_status) {
		this.employment_status = employment_status;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public String getEnglish_class() {
		return english_class;
	}
	public void setEnglish_class(String english_class) {
		this.english_class = english_class;
	}
	public Date getGraduated_time() {
		return graduated_time;
	}
	public void setGraduated_time(Date graduated_time) {
		this.graduated_time = graduated_time;
	}
	public String getGraduated_university() {
		return graduated_university;
	}
	public void setGraduated_university(String graduated_university) {
		this.graduated_university = graduated_university;
	}
	public String getHome_phone() {
		return home_phone;
	}
	public void setHome_phone(String home_phone) {
		this.home_phone = home_phone;
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
	public String getPolitical() {
		return political;
	}
	public void setPolitical(String political) {
		this.political = political;
	}
	public String getSchool_class() {
		return school_class;
	}
	public void setSchool_class(String school_class) {
		this.school_class = school_class;
	}
	public String getSchool_major() {
		return school_major;
	}
	public void setSchool_major(String school_major) {
		this.school_major = school_major;
	}
	public String getStudent_phone() {
		return student_phone;
	}
	public void setStudent_phone(String student_phone) {
		this.student_phone = student_phone;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassRoom() {
		return classRoom;
	}
	public void setClassRoom(String classRoom) {
		this.classRoom = classRoom;
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
