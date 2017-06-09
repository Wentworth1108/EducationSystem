package cn.gcs.rest.vo;

import java.io.Serializable;
import java.util.Date;

public class RestItem implements Serializable{
	
	private Integer restId;
	private String student_id;
	private String name;
	private String className;
	private Date start_time;
	private Date end_time;
	private String reason;
	private String state;
	private String userName;
	private int days;
	
	

	public Integer getRestId() {
		return restId;
	}
	public void setRestId(Integer restId) {
		this.restId = restId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
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
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getDays() {
		return days;
	}
	public void setDays(Date start_time, Date end_time) {
		days = (int) ((end_time.getTime()-start_time.getTime())/(1000*60*60*24));
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	
}
