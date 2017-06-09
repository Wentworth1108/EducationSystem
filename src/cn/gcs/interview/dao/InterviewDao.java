package cn.gcs.interview.dao;

import java.util.List;

import cn.gcs.interview.entity.Interview;

public interface InterviewDao {

	List<Interview> queryInterview(int page, int rows);

	int getTotalNumberOfInterview();

	void insertInterview(Interview interview);

	void updateInterview(Interview interview);

	void deleteInterview(Interview interview);

	Interview getById(int id);

	List<Interview> queryOldInterview(int page, int rows);

	int getTotalNumberOfOldInterview();

}
