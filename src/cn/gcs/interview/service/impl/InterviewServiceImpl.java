package cn.gcs.interview.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.gcs.interview.dao.InterviewDao;
import cn.gcs.interview.dao.InterviewRecordDao;
import cn.gcs.interview.entity.Interview;
import cn.gcs.interview.entity.InterviewRecord;
import cn.gcs.interview.service.InterviewService;
import cn.gcs.interview.vo.InterviewRecordItem;
import cn.gcs.student.dao.StudentDao;

@Component
@Transactional
public class InterviewServiceImpl implements InterviewService {

	@Autowired
	private InterviewDao interviewDao;

	@Autowired
	private InterviewRecordDao interviewRecordDao;

	@Autowired
	private StudentDao studentDao;

	@Override
	public List<Interview> getInterview(int page, int rows) {
		return interviewDao.queryInterview(page, rows);
	}

	@Override
	public int getTotalNumberOfInterview() {
		return interviewDao.getTotalNumberOfInterview();
	}

	@Override
	public void addInterview(String company, String job, String address,
			Date date, Integer number, Date failTime) {
		interviewDao.insertInterview(new Interview(company, job, address, date,
				number, failTime));

	}

	@Override
	public void modifyInterview(Integer id, String company, String job,
			String address, Date date, Integer number, Date failTime) {
		Interview data = interviewDao.getById(id);
		data.setCompany(company);
		data.setJob(job);
		data.setAddress(address);
		data.setDate(date);
		data.setNumber(number);
		data.setFailTime(failTime);
		interviewDao.updateInterview(data);
	}

	@Override
	public void removeInterview(List<Integer> list) {
		for (int id : list) {
			interviewDao.deleteInterview(interviewDao.getById(id));
		}
	}

	@Override
	public List<Interview> getOldInterview(int page, int rows) {
		return interviewDao.queryOldInterview(page, rows);
	}

	@Override
	public int getTotalNumberOfOldInterview() {
		return interviewDao.getTotalNumberOfOldInterview();
	}

	@Override
	public void addInterviewApply(int userid, Integer interviewid) {
		interviewRecordDao.insertInterviewRecord(new InterviewRecord(studentDao
				.getById(userid), interviewDao.getById(interviewid)));
	}

	@Override
	public List<InterviewRecordItem> getInterviewApply(int page, int rows) {
		List<InterviewRecordItem> result = new ArrayList<InterviewRecordItem>();
		for (InterviewRecord i : interviewRecordDao.queryInterviewApply(page,
				rows)) {
			InterviewRecordItem temp = new InterviewRecordItem();
			temp.setId(i.getId());
			temp.setPerson(i.getStudent().getUser().getName());
			temp.setPersonid(i.getStudent().getStudentId());
			temp.setInterviewid(i.getInterview().getId());
			temp.setCompany(i.getInterview().getCompany());
			temp.setJob(i.getInterview().getJob());
			temp.setAddress(i.getInterview().getAddress());
			temp.setDate(i.getInterview().getDate());

			result.add(temp);
		}
		return result;
	}

	@Override
	public int getTotalNumberOfInterviewApply() {
		return interviewRecordDao.getTotalNumberOfInterviewApply();
	}

	@Override
	public void approvalInterview(String handle, List<Integer> id) {
		if (handle.equals("pass")) {
			for (int i : id) {
				InterviewRecord temp = interviewRecordDao.getById(i);
				temp.setState("2");
				interviewRecordDao.update(temp);
			}
		} else if (handle.equals("refuse")) {
			for (int i : id) {
				InterviewRecord temp = interviewRecordDao.getById(i);
				temp.setState("3");
				interviewRecordDao.update(temp);
			}
		}
	}

	@Override
	public List<InterviewRecordItem> getMyInterviewApply(int userid, int page,
			int rows) {
		List<InterviewRecordItem> result = new ArrayList<InterviewRecordItem>();
		for (InterviewRecord i : interviewRecordDao
				.queryInterviewApplyByStudentid(userid, page, rows)) {
			InterviewRecordItem temp = new InterviewRecordItem();
			temp.setId(i.getId());
			temp.setInterviewid(i.getInterview().getId());
			temp.setCompany(i.getInterview().getCompany());
			temp.setJob(i.getInterview().getJob());
			temp.setAddress(i.getInterview().getAddress());
			temp.setDate(i.getInterview().getDate());
			temp.setState(i.getState());
			temp.setSummary(i.getSummary());

			result.add(temp);
		}
		return result;
	}

	@Override
	public int getTotalNumberOfMyInterviewApply(int userid) {
		return interviewRecordDao.getTotalNumberOfMyInterviewApply(userid);
	}

	@Override
	public void addFeedback(Integer id, String state, String summary) {
		InterviewRecord temp = interviewRecordDao.getById(id);
		if (state.equals("pass")) {
			temp.setState("4");
		} else if (state.equals("refuse")) {
			temp.setState("5");
		}
		temp.setSummary(summary);
		interviewRecordDao.update(temp);
	}

	@Override
	public List<InterviewRecordItem> getInterviewRecord(int page, int rows) {
		List<InterviewRecordItem> result = new ArrayList<InterviewRecordItem>();
		for (InterviewRecord i : interviewRecordDao.queryInterviewRecord(page,
				rows)) {
			InterviewRecordItem temp = new InterviewRecordItem();
			temp.setId(i.getId());
			temp.setPerson(i.getStudent().getUser().getName());
			temp.setPersonid(i.getStudent().getStudentId());
			temp.setInterviewid(i.getInterview().getId());
			temp.setCompany(i.getInterview().getCompany());
			temp.setJob(i.getInterview().getJob());
			temp.setAddress(i.getInterview().getAddress());
			temp.setDate(i.getInterview().getDate());
			temp.setState(i.getState());
			temp.setSummary(i.getSummary());

			result.add(temp);
		}
		return result;
	}

	@Override
	public int getTotalNumberOfInterviewRecord() {
		return interviewRecordDao.getTotalNumberOfInterviewRecord();
	}
}
