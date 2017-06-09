package cn.gcs.employment.service;

import java.util.Date;
import java.util.List;

import cn.gcs.employment.entity.GiveupEmployment;
import cn.gcs.employment.entity.PutoffEmployment;
import cn.gcs.employment.vo.GiveupEmploymentRecordItem;
import cn.gcs.employment.vo.PutoffEmploymentRecordItem;

public interface EmploymentService {

	void applyPutoff(int studentid, Date leaveDate, Date backDate, String reason);

	List<PutoffEmploymentRecordItem> getPutoffEmploymentApply(int page,
			int rows, int userid);

	int getTotalNumberOfPutoffApply(int userid);

	void approvalPutoff(String handle, List<Integer> id, int userid);

	List<PutoffEmploymentRecordItem> getPutoffRecord(int page, int rows);

	int getTotalNumberOfPutoffRecord();

	void applyGiveup(int studentid, String reason);

	List<GiveupEmploymentRecordItem> getGiveupEmploymentApply(int page,
			int rows, int userid);

	int getTotalNumberOfGiveupApply(int userid);

	void approvalGiveup(String handle, List<Integer> id, int userid);

	List<GiveupEmploymentRecordItem> getGiveupRecord(int page, int rows);

	int getTotalNumberOfGiveupRecord();

	List<PutoffEmployment> getPutoffRecord(int userid);

	List<GiveupEmployment> getGiveupRecord(int userid);

}
