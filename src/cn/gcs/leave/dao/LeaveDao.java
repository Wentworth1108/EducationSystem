package cn.gcs.leave.dao;

import java.util.List;

import cn.gcs.leave.entity.LeaveRecord;

public interface LeaveDao {

	void insertRecord(LeaveRecord leave);

	List<LeaveRecord> getLeaveApply(int page, int rows, String role);

	int getTotalNumberOfLeaveApply(String role);

	LeaveRecord getLeaveById(int id);

	void updateLeave(LeaveRecord leave);

	List<LeaveRecord> getLeaveRecord(int page, int rows);

	int getTotalNumberOfLeaveRecord();

	List<LeaveRecord> getLeaveRecord(int userid);

}
