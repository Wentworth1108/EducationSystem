package cn.gcs.interview.action;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.gcs.interview.service.InterviewService;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class AddInterview extends ActionSupport {

	@Autowired
	private InterviewService interviewService;

	private String company;
	private String job;
	private String address;
	private Date date;
	private Integer number;
	private Date failTime;

	@Override
	public String execute() throws Exception {
		interviewService.addInterview(company, job, address, date, number,
				failTime);
		return NONE;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public void setFailTime(Date failTime) {
		this.failTime = failTime;
	}
}
