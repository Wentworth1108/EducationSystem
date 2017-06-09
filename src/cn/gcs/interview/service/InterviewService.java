package cn.gcs.interview.service;

import java.util.Date;
import java.util.List;

import cn.gcs.interview.entity.Interview;
import cn.gcs.interview.vo.InterviewRecordItem;

public interface InterviewService {

	List<Interview> getInterview(int page, int rows);

	int getTotalNumberOfInterview();

	void addInterview(String company, String job, String address, Date date,
			Integer number, Date failTime);

	void modifyInterview(Integer id, String company, String job,
			String address, Date date, Integer number, Date failTime);

	void removeInterview(List<Integer> list);

	List<Interview> getOldInterview(int page, int rows);

	int getTotalNumberOfOldInterview();

	void addInterviewApply(int userid, Integer interviewid);

	List<InterviewRecordItem> getInterviewApply(int page, int rows);

	int getTotalNumberOfInterviewApply();

	void approvalInterview(String handle, List<Integer> id);

	List<InterviewRecordItem> getMyInterviewApply(int userid, int page, int rows);

	int getTotalNumberOfMyInterviewApply(int userid);

	void addFeedback(Integer id, String state, String summary);

	List<InterviewRecordItem> getInterviewRecord(int page, int rows);

	int getTotalNumberOfInterviewRecord();

}
