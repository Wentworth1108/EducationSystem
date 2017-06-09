package cn.gcs.user.entity;

import java.util.Date;
import java.util.Set;

public class User {

	private Integer id;
	private String name;
	private boolean gender;
	private Date birthday;
	private String idNumber;
	private String email;
	private String mobile;
	private String account;
	private String password;
	private String state;

	private Set<UserRole> userRoles;

	//用户状态
	public static String USER_STATE_VALID = "1"; // 有效
	public static String USER_STATE_INVALID = "0"; // 无效

	public User() {
	}

	public User(Integer id) {
		this.id = id;
	}

	public User(Integer id, String name, boolean gender, Date birthday,
			String idNumber, String email, String mobile, String account,
			String password, String state, Set<UserRole> userRoles) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.birthday = birthday;
		this.idNumber = idNumber;
		this.email = email;
		this.mobile = mobile;
		this.account = account;
		this.password = password;
		this.state = state;
		this.userRoles = userRoles;
	}

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

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

}
