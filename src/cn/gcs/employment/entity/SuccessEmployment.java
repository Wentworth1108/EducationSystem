package cn.gcs.employment.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import cn.gcs.student.entity.Student;

@Entity
public class SuccessEmployment implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer successEmploymentId;

	@ManyToOne
	private Student student;
	private String company;
	private String job;
	private Date date;
	private Double pay;

	public SuccessEmployment() {
	}

	public SuccessEmployment(Student student, String company, String job,
			Date date, Double pay) {
		super();
		this.student = student;
		this.company = company;
		this.job = job;
		this.date = date;
		this.pay = pay;
	}

	public Integer getSuccessEmploymentId() {
		return successEmploymentId;
	}

	public void setSuccessEmploymentId(Integer successEmploymentId) {
		this.successEmploymentId = successEmploymentId;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getPay() {
		return pay;
	}

	public void setPay(Double pay) {
		this.pay = pay;
	}

	@Override
	public String toString() {
		return "SuccessEmployment [successEmploymentId=" + successEmploymentId
				+ ", student=" + student + ", company=" + company + ", job="
				+ job + ", date=" + date + ", pay=" + pay + "]";
	}

}
