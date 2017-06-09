package cn.gcs.course.entity;

import java.io.Serializable;

public class CourseTeacher implements Serializable {

	private CourseTeacherId id;

	public CourseTeacher() {
	}
	
	public CourseTeacher(CourseTeacherId id) {
		this.setId(id);
	}

	public CourseTeacherId getId() {
		return id;
	}
	public void setId(CourseTeacherId id) {
		this.id = id;
	}

	
}
