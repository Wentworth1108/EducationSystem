package cn.gcs.employment.dao;

import java.util.List;

import cn.gcs.employment.entity.GiveupEmployment;
import cn.gcs.employment.entity.PutoffEmployment;

public interface EmploymentDao {

	void insertPutoffRecord(PutoffEmployment putoffEmployment);

	List<PutoffEmployment> getPutoffApply(int page, int rows, String role);

	int getTotalNumberOfPutoffApply(String role);

	PutoffEmployment getPutoffById(int id);

	void updatePutoff(PutoffEmployment putoffEmployment);

	List<PutoffEmployment> getPutoffRecord(int page, int rows);

	int getTotalNumberOfPutoffRecord();

	void insertGiveupRecord(GiveupEmployment giveupEmployment);

	List<GiveupEmployment> getGiveupApply(int page, int rows, String role);

	int getTotalNumberOfGiveupApply(String role);

	GiveupEmployment getGiveupById(int id);

	void updateGiveup(GiveupEmployment giveupEmployment);

	List<GiveupEmployment> getGiveupRecord(int page, int rows);

	int getTotalNumberOfGiveupRecord();

	List<PutoffEmployment> getPutoffRecord(int userid);

	List<GiveupEmployment> getGiveupRecord(int userid);

}
