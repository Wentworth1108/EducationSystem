package cn.gcs.score.entity;

import java.io.Serializable;

import cn.gcs.exam.entity.Exam;
import cn.gcs.student.entity.Student;

public class Score implements Serializable {

	private Integer scoreId;
	
	private Exam exam;
	private Student student;
	
	private Float score;
	private String comment;
	
	
	public Score() {
	}

	public Score(Integer scoreId, Exam exam, Student student, Float score, String comment) {
		this.scoreId = scoreId;
		this.exam = exam;
		this.student = student;
		this.score = score;
		this.comment = comment;
	}

	public Integer getScoreId() {
		return scoreId;
	}
	public void setScoreId(Integer scoreId) {
		this.scoreId = scoreId;
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public Exam getExam() {
		return exam;
	}
	public void setExam(Exam exam) {
		this.exam = exam;
	}
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}

	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}

	
}
