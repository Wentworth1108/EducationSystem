package cn.gcs.interview.dao;

import java.util.List;

import cn.gcs.interview.entity.InterviewRecord;

public interface InterviewRecordDao {

	void insertInterviewRecord(InterviewRecord interviewRecord);

	List<InterviewRecord> queryInterviewApply(int page, int rows);

	int getTotalNumberOfInterviewApply();

	InterviewRecord getById(int id);

	void update(InterviewRecord interviewRecord);

	List<InterviewRecord> queryInterviewApplyByStudentid(int studentid,
			int page, int rows);

	int getTotalNumberOfMyInterviewApply(int studentid);

	List<InterviewRecord> queryInterviewRecord(int page, int rows);

	int getTotalNumberOfInterviewRecord();

}
