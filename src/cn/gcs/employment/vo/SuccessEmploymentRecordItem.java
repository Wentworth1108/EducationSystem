package cn.gcs.employment.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SuccessEmploymentRecordItem {
	private Integer id;
	private Integer studentid;
	private String name;
	private String gender;
	private String phone;
	private String school;
	private String education;
	private String company;
	private String job;
	private String date;
	private Double pay;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Double getPay() {
		return pay;
	}

	public void setPay(Double pay) {
		this.pay = pay;
	}

	public String getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = SimpleDateFormat.getDateInstance().format(date);
	}

	public Integer getStudentid() {
		return studentid;
	}

	public void setStudentid(Integer studentid) {
		this.studentid = studentid;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
