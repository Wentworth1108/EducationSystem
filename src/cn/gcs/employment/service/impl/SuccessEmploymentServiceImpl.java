package cn.gcs.employment.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.gcs.employment.dao.SuccessEmploymentDao;
import cn.gcs.employment.entity.SuccessEmployment;
import cn.gcs.employment.service.SuccessEmploymentService;
import cn.gcs.employment.vo.SuccessEmploymentRecordItem;
import cn.gcs.student.dao.StudentDao;
import cn.gcs.student.entity.Student;

@Component
@Transactional
public class SuccessEmploymentServiceImpl implements SuccessEmploymentService {

	@Autowired
	private SuccessEmploymentDao successEmploymentDao;

	@Autowired
	private StudentDao studentDao;

	@Override
	public List<SuccessEmploymentRecordItem> getSuccessEmploymentRecord(
			int page, int rows, Map<String, Object> condition) {
		List<SuccessEmploymentRecordItem> result = new ArrayList<SuccessEmploymentRecordItem>();
		for (SuccessEmployment s : successEmploymentDao.queryEmploymentRecord(
				page, rows, condition)) {
			SuccessEmploymentRecordItem temp = new SuccessEmploymentRecordItem();
			temp.setStudentid(s.getStudent().getStudentId());
			temp.setName(s.getStudent().getUser().getName());
			temp.setPhone(s.getStudent().getUser().getMobile());
			temp.setSchool(s.getStudent().getGraduated_university());
			temp.setEducation(s.getStudent().getEducational_background());

			temp.setId(s.getSuccessEmploymentId());
			temp.setCompany(s.getCompany());
			temp.setJob(s.getJob());
			temp.setDate(s.getDate());
			temp.setPay(s.getPay());

			result.add(temp);
		}
		return result;
	}

	@Override
	public void addRecord(int studentid, String company, String job, Date date,
			Double pay) {
		Student s = studentDao.getById(studentid);
		successEmploymentDao.insertRecord(new SuccessEmployment(s, company,
				job, date, pay));
	}

	@Override
	public void modifyRecord(int id, int studentid, String company, String job,
			Date date, Double pay) {
		SuccessEmployment data = successEmploymentDao.getById(id);
		data.setStudent(studentDao.getById(studentid));
		data.setCompany(company);
		data.setJob(job);
		data.setDate(date);
		data.setPay(pay);
		successEmploymentDao.updateRecord(data);
	}

	@Override
	public void removeRecord(List<Integer> list) {
		for (int id : list) {
			successEmploymentDao.deleteRecord(successEmploymentDao.getById(id));
		}
	}

	@Override
	public int getTotalNumberOfRecord(Map<String, Object> condition) {
		return successEmploymentDao.getTotalNumberOfRecord(condition);
	}

}
