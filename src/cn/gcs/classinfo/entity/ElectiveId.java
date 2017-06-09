package cn.gcs.classinfo.entity;

import java.io.Serializable;

import cn.gcs.course.entity.Course;

public class ElectiveId implements Serializable {

	private Course course;
	private Integer classId;
	
	public ElectiveId() {
	}
	
	public ElectiveId(Course course, Integer classId) {
		this.course = course;
		this.classId = classId;
	}
	
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	public Integer getClassId() {
		return classId;
	}
	public void setClassId(Integer classId) {
		this.classId = classId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((classId == null) ? 0 : classId.hashCode());
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ElectiveId other = (ElectiveId) obj;
		if (classId == null) {
			if (other.classId != null)
				return false;
		} else if (!classId.equals(other.classId))
			return false;
		if (course == null) {
			if (other.course != null)
				return false;
		} else if (!course.equals(other.course))
			return false;
		return true;
	}
}
