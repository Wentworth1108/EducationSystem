package cn.gcs.leave.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import cn.gcs.student.entity.Student;

@Entity
public class LeaveRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	private Student student;

	private Date date;
	private String reason;

	private String banzhuren;
	private String jiaowubu;
	private String jiuyebu;
	private String caiwubu;
	private String houqinbu;

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getBanzhuren() {
		return banzhuren;
	}

	public void setBanzhuren(String banzhuren) {
		this.banzhuren = banzhuren;
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

	public String getCaiwubu() {
		return caiwubu;
	}

	public void setCaiwubu(String caiwubu) {
		this.caiwubu = caiwubu;
	}

	public String getHouqinbu() {
		return houqinbu;
	}

	public void setHouqinbu(String houqinbu) {
		this.houqinbu = houqinbu;
	}

}
