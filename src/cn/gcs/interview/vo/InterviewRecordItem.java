package cn.gcs.interview.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InterviewRecordItem {
	private Integer id;
	private String person;
	private Integer personid;
	private Integer interviewid;
	private String company;
	private String job;
	private String address;
	private String date;
	private String summary;
	private String state;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public Integer getPersonid() {
		return personid;
	}

	public void setPersonid(Integer personid) {
		this.personid = personid;
	}

	public Integer getInterviewid() {
		return interviewid;
	}

	public void setInterviewid(Integer interviewid) {
		this.interviewid = interviewid;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = SimpleDateFormat.getDateInstance().format(date);
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		if (state.equals("1")) {
			this.state = "待审批";
		} else if (state.equals("2")) {
			this.state = "待参加";
		} else if (state.equals("3")) {
			this.state = "已拒绝";
		} else if (state.equals("4")) {
			this.state = "面试通过";
		} else if (state.equals("5")) {
			this.state = "面试失败";
		}
	}

}
