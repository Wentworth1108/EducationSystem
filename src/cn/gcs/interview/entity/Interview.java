package cn.gcs.interview.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.struts2.json.annotations.JSON;

@Entity
public class Interview {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String company;
	private String job;
	private String address;
	private Date date;
	private Integer number;
	private Date failTime;

	public Interview() {
	}

	public Interview(String company, String job, String address, Date date,
			Integer number, Date failTime) {
		super();
		this.company = company;
		this.job = job;
		this.address = address;
		this.date = date;
		this.number = number;
		this.failTime = failTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	@JSON(format = "yyyy-MM-dd")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getFailTime() {
		return failTime;
	}

	public void setFailTime(Date failTime) {
		this.failTime = failTime;
	}
}
