package cn.gcs.employment.action;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.gcs.employment.service.SuccessEmploymentService;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class AddSuccessEmploymentRecord extends ActionSupport {

	@Autowired
	private SuccessEmploymentService successEmploymentService;

	private int studentid;
	private String company;
	private String job;
	private Date date;
	private Double pay;

	@Override
	public String execute() throws Exception {
		successEmploymentService.addRecord(studentid, company, job, date, pay);
		return NONE;
	}

	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setPay(Double pay) {
		this.pay = pay;
	}

}
