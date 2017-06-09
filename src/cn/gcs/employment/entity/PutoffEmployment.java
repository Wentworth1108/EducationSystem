package cn.gcs.employment.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import cn.gcs.student.entity.Student;

@Entity
public class PutoffEmployment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	private Student student;

	private Date leaveDate;
	private Date backDate;
	private String reason;

	private String jiaowubu;
	private String jiuyebu;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	public Date getBackDate() {
		return backDate;
	}

	public void setBackDate(Date backDate) {
		this.backDate = backDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getJiaowubu() {
		return jiaowubu;
	}

	public void setJiaowubu(String jiaowubu) {
		this.jiaowubu = jiaowubu;
	}

	public String getJiuyebu() {
		return jiuyebu;
	}

	public void setJiuyebu(String jiuyebu) {
		this.jiuyebu = jiuyebu;
	}

}
