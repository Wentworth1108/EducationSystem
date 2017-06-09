package cn.gcs.change.vo;

import java.io.Serializable;
import java.util.Date;

public class ChangeItem implements Serializable{
	
    private Integer changeId;
    private String student_id;
    private String name;
    private String school;
    private String className;
    private String newClass;
    private Date date;
    private String reason;
    private String approve;
    
    private Integer studentId;
    private Integer classId;
    
    
    
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	public Integer getChangeId() {
		return changeId;
	}
	public void setChangeId(Integer changeId) {
		this.changeId = changeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getNewClass() {
		return newClass;
	}
	public void setNewClass(String newClass) {
		this.newClass = newClass;
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
	public String getApprove() {
		return approve;
	}
	public void setApprove(String approve) {
		this.approve = approve;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
    
    
}
