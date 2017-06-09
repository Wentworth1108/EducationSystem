package cn.gcs.exam.entity;

import java.io.Serializable;
import java.util.Date;

public class Exam implements Serializable {

	private Integer examId;
	private String title;
	private String courseName;
	private String className;
	private Date startTime;
	private Date endTime;
	private String memo;
	private String analysis;
	
	public Exam() {
	}
	
	public Exam(Integer examId) {
		this.examId = examId;
	}

	public Exam(Integer examId, String title, String courseName, String className, Date startTime, Date endTime,
			String memo, String analysis) {
		this.examId = examId;
		this.title = title;
		this.courseName = courseName;
		this.className = className;
		this.startTime = startTime;
		this.endTime = endTime;
		this.memo = memo;
		this.analysis = analysis;
	}

	public Integer getExamId() {
		return examId;
	}
	public void setExamId(Integer examId) {
		this.examId = examId;
	}
	
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getAnalysis() {
		return analysis;
	}
	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

}
