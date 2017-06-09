package cn.gcs.leave.service;

import java.util.Date;
import java.util.List;

import cn.gcs.leave.entity.LeaveRecord;
import cn.gcs.leave.vo.LeaveRecordItem;

public interface LeaveService {

	void applyLeave(int studentid, Date date, String reason);

	List<LeaveRecordItem> getLeaveApply(int page, int rows, int userid);

	int getTotalNumberOfLeaveApply(int userid);

	void approvalLeave(String handle, List<Integer> id, int userid);

	List<LeaveRecordItem> getLeaveRecord(int page, int rows);

	int getTotalNumberOfLeaveRecord();

	List<LeaveRecord> getLeaveRecord(int userid);

}
