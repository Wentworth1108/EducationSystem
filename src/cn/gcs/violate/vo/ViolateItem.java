package cn.gcs.violate.vo;

import java.io.Serializable;
import java.util.Date;

import cn.gcs.student.entity.Student;

public class ViolateItem implements Serializable{

	private Integer violateId;
	private String student_id;
	private String violateItem;
	private Date  violate_date;
	private double remark;
	private String result;
	private String stuName;
	private String className;
	private String memo;

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

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getStudent_id() {
		return student_id;
	}

	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	
}
