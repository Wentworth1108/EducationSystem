package cn.gcs.employment.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PutoffEmploymentRecordItem {
	private Integer id;
	private Integer personid;
	private String name;
	private String phone;
	private String address;
	private String school;
	private String leavedate;
	private String backdate;
	private String reason;

	private String jiaowubu;
	private String jiuyebu;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getLeavedate() {
		return leavedate;
	}

	public void setLeavedate(Date leavedate) {
		this.leavedate = SimpleDateFormat.getDateInstance().format(leavedate);
	}

	public String getBackdate() {
		return backdate;
	}

	public void setBackdate(Date backdate) {
		this.backdate = SimpleDateFormat.getDateInstance().format(backdate);
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getPersonid() {
		return personid;
	}

	public void setPersonid(Integer personid) {
		this.personid = personid;
	}

	public String getJiaowubu() {
		return jiaowubu;
	}

	public void setJiaowubu(String jiaowubu) {
		if (jiaowubu.equals("0")) {
			this.jiaowubu = "待审批";
		} else if (jiaowubu.equals("1")) {
			this.jiaowubu = "通过";
		} else if (jiaowubu.equals("2")) {
			this.jiaowubu = "拒绝";
		}
	}

	public String getJiuyebu() {
		return jiuyebu;
	}

	public void setJiuyebu(String jiuyebu) {
		if (jiuyebu.equals("0")) {
			this.jiuyebu = "待审批";
		} else if (jiuyebu.equals("1")) {
			this.jiuyebu = "通过";
		} else if (jiuyebu.equals("2")) {
			this.jiuyebu = "拒绝";
		}
	}
}
