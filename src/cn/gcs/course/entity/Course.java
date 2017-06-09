package cn.gcs.course.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class Course implements Serializable {

	private Integer courseId;
	private String name;

	private Set<CourseTeacher> courseTeachers;
	
	public Course() {
	}

	public Course(Integer courseId) {
		this.courseId = courseId;
	}

	public Course(Integer courseId, String name, Set<CourseTeacher> courseTeachers) {
		this.courseId = courseId;
		this.name = name;
		this.courseTeachers = courseTeachers;
	}
	
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Set<CourseTeacher> getCourseTeachers() {
		return courseTeachers;
	}
	public void setCourseTeachers(Set<CourseTeacher> courseTeachers) {
		this.courseTeachers = courseTeachers;
	}
	
}
