package cn.gcs.employment.dao;

import java.util.List;
import java.util.Map;

import cn.gcs.employment.entity.SuccessEmployment;

public interface SuccessEmploymentDao {

	List<SuccessEmployment> queryEmploymentRecord(int page, int rows,
			Map<String, Object> condition);

	int getTotalNumberOfRecord(Map<String, Object> condition);

	void insertRecord(SuccessEmployment successEmployment);

	void updateRecord(SuccessEmployment successEmployment);

	void deleteRecord(SuccessEmployment successEmployment);

	SuccessEmployment getById(int id);

}