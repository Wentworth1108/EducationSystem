package cn.gcs.employment.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.gcs.employment.vo.SuccessEmploymentRecordItem;

public interface SuccessEmploymentService {

	List<SuccessEmploymentRecordItem> getSuccessEmploymentRecord(int page,
			int rows, Map<String, Object> condition);

	int getTotalNumberOfRecord(Map<String, Object> condition);

	void addRecord(int studentid, String company, String job, Date date,
			Double pay);

	void modifyRecord(int id, int studentid, String company, String job,
			Date date, Double pay);

	void removeRecord(List<Integer> list);

}
