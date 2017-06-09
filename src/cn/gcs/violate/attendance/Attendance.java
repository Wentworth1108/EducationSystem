package cn.gcs.violate.attendance;

import java.io.Serializable;
import java.util.Date;

public class Attendance implements Serializable {
	
	private Integer id;
	private String name;
	private String className;
	private String school;
	private int beLate;
	private int beEarly;
	private int absenteeism;	
	private Date date;
	
	
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
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public int getAbsenteeism() {
		return absenteeism;
	}
	public void setAbsenteeism(int absenteeism) {
		this.absenteeism = absenteeism;
	}
	public int getBeLate() {
		return beLate;
	}
	public void setBeLate(int beLate) {
		this.beLate = beLate;
	}
	public int getBeEarly() {
		return beEarly;
	}
	public void setBeEarly(int beEarly) {
		this.beEarly = beEarly;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
