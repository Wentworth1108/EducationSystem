package cn.gcs.talk.vo;

import java.io.Serializable;
import java.util.Date;

public class TalkItem implements Serializable{
	
	private Integer talkId;
	private String description;
	private String student_id;
	private Date date;
	private Date start_time;
	private Date end_time;
	
	private String stuName;
	private String teaName;
	private String className;
	
	
	public Integer getTalkId() {
		return talkId;
	}
	public void setTalkId(Integer talkId) {
		this.talkId = talkId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getStart_time() {
		return start_time;
	}
	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getTeaName() {
		return teaName;
	}
	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	
}
