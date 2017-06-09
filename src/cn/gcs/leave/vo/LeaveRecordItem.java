package cn.gcs.leave.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LeaveRecordItem {
	private Integer id;
	private Integer personid;
	private String name;
	private String phone;
	private String school;
	private String date;
	private String reason;

	private String jiaowubu;
	private String jiuyebu;
	private String caiwubu;
	private String houqinbu;
	private String banzhuren;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPersonid() {
		return personid;
	}

	public void setPersonid(Integer personid) {
		this.personid = personid;
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

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = SimpleDateFormat.getDateInstance().format(date);
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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

	public String getCaiwubu() {
		return caiwubu;
	}

	public void setCaiwubu(String caiwubu) {
		if (caiwubu.equals("0")) {
			this.caiwubu = "待审批";
		} else if (caiwubu.equals("1")) {
			this.caiwubu = "通过";
		} else if (caiwubu.equals("2")) {
			this.caiwubu = "拒绝";
		}
	}

	public String getHouqinbu() {
		return houqinbu;
	}

	public void setHouqinbu(String houqinbu) {
		if (houqinbu.equals("0")) {
			this.houqinbu = "待审批";
		} else if (houqinbu.equals("1")) {
			this.houqinbu = "通过";
		} else if (houqinbu.equals("2")) {
			this.houqinbu = "拒绝";
		}
	}

	public String getBanzhuren() {
		return banzhuren;
	}

	public void setBanzhuren(String banzhuren) {
		if (banzhuren.equals("0")) {
			this.banzhuren = "待审批";
		} else if (banzhuren.equals("1")) {
			this.banzhuren = "通过";
		} else if (banzhuren.equals("2")) {
			this.banzhuren = "拒绝";
		}
	}
}
