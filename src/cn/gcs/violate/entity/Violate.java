package cn.gcs.violate.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import cn.gcs.student.entity.Student;


@Entity
public class Violate implements Serializable{
	
	
	private Integer violateId;
	private String violateItem;
	private Date  violate_date;
	private double remark;
	private String result;
	//备注
	private String memo;
	//类型
	private Integer type;
	

	private Student student;
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getViolateId() {
		return violateId;
	}
	public void setViolateId(Integer violateId) {
		this.violateId = violateId;
	}
	public String getViolateItem() {
		return violateItem;
	}
	public void setViolateItem(String violateItem) {
		this.violateItem = violateItem;
	}
	public Date getViolate_date() {
		return violate_date;
	}
	public void setViolate_date(Date violate_date) {
		this.violate_date = violate_date;
	}
	public double getRemark() {
		return remark;
	}
	public void setRemark(double remark) {
		this.remark = remark;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	@ManyToOne(targetEntity = Student.class)
	@JoinColumn(name="studentId")
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
}
