package cn.gcs.core.constant;

import java.util.HashMap;
import java.util.Map;


public class Constant {

	// 系统中用户在session中的标识符
	public static String USER = "SYS_USER";	
	
	/*----------------------系统权限集合--------------------------*/
	public static String PRIVILEGE_SYSTEM = "system"; 
	public static String PRIVILEGE_STUDENT = "student"; 
	public static String PRIVILEGE_DAILY = "daily"; 
	public static String PRIVILEGE_CLASS = "class"; 
	public static String PRIVILEGE_SCORE = "score"; 
	public static String PRIVILEGE_EMPLOYEE = "employee"; 
	public static String PRIVILEGE_LEAVE = "leave"; 

	public static Map<String, String> PRIVILEGE_MAP;
	static {
		PRIVILEGE_MAP = new HashMap<String, String>();
		PRIVILEGE_MAP.put(PRIVILEGE_SYSTEM, "系统管理");
		PRIVILEGE_MAP.put(PRIVILEGE_STUDENT, "学生信息");
		PRIVILEGE_MAP.put(PRIVILEGE_DAILY, "日常管理");
		PRIVILEGE_MAP.put(PRIVILEGE_CLASS, "班级管理");
		PRIVILEGE_MAP.put(PRIVILEGE_SCORE, "成绩管理");
		PRIVILEGE_MAP.put(PRIVILEGE_EMPLOYEE, "就业管理");
		PRIVILEGE_MAP.put(PRIVILEGE_LEAVE, "离园管理");
	}
}
