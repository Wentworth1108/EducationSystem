package cn.gcs.classinfo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.gcs.student.entity.Student;
import cn.gcs.user.entity.User;

public class ClassInfo implements Serializable {
	
	private Integer classId;
	private String name;
	private String classroom;
	
	private User user;
	
	private Set<Elective> electives;
	
	public ClassInfo() {
	}
	
	public ClassInfo(Integer classId, String name, String classroom, User user, Set<Elective> electives) {
		this.classId = classId;
		this.name = name;
		this.classroom = classroom;
		this.user = user;
		this.electives = electives;
	}

	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getClassroom() {
		return classroom;
	}
	public void setClassroom(String classroom) {
		this.classroom = classroom;
	}
	
	public Set<Elective> getElectives() {
		return electives;
	}
	public void setElectives(Set<Elective> electives) {
		this.electives = electives;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
