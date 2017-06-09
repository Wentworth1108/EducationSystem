package cn.gcs.change.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.gcs.classinfo.entity.ClassInfo;
import cn.gcs.student.entity.Student;

@Entity
@Table(name="class_change")
public class Change implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer changeId;
	
	@ManyToOne
	private Student student;
	
	private String reason;
	
	private Date date;
	
	@ManyToOne
	private ClassInfo classInfo;
	
	private String approve;

	
	
	
	public Integer getChangeId() {
		return changeId;
	}

	public void setChangeId(Integer changeId) {
		this.changeId = changeId;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


	public ClassInfo getClassInfo() {
		return classInfo;
	}

	public void setClassInfo(ClassInfo classInfo) {
		this.classInfo = classInfo;
	}

	public String getApprove() {
		return approve;
	}

	public void setApprove(String approve) {
		this.approve = approve;
	}

	
	
}
