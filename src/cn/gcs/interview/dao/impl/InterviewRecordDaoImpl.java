package cn.gcs.interview.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.gcs.interview.dao.InterviewRecordDao;
import cn.gcs.interview.entity.InterviewRecord;

@Component
public class InterviewRecordDaoImpl implements InterviewRecordDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void insertInterviewRecord(InterviewRecord interviewRecord) {
		sessionFactory.getCurrentSession().save(interviewRecord);
	}

	@Override
	public List<InterviewRecord> queryInterviewApply(int page, int rows) {
		return sessionFactory.getCurrentSession()
				.createQuery("from InterviewRecord i where i.state = 1")
				.setFirstResult(rows * (page - 1)).setMaxResults(rows).list();
	}

	@Override
	public int getTotalNumberOfInterviewApply() {
		return ((Long) sessionFactory
				.getCurrentSession()
				.createQuery(
						"select count(1) from InterviewRecord i where i.state = 1")
				.uniqueResult()).intValue();
	}

	@Override
	public InterviewRecord getById(int id) {
		return sessionFactory.getCurrentSession()
				.get(InterviewRecord.class, id);
	}

	@Override
	public void update(InterviewRecord interviewRecord) {
		sessionFactory.getCurrentSession().update(interviewRecord);
	}

	@Override
	public List<InterviewRecord> queryInterviewApplyByStudentid(int studentid,
			int page, int rows) {
		return sessionFactory
				.getCurrentSession()
				.createQuery(
						"from InterviewRecord i where i.student.user.id = ?0")
				.setParameter("0", studentid).setFirstResult(rows * (page - 1))
				.setMaxResults(rows).list();
	}

	@Override
	public int getTotalNumberOfMyInterviewApply(int studentid) {
		return ((Long) sessionFactory
				.getCurrentSession()
				.createQuery(
						"select count(1) from InterviewRecord i where i.student.user.id = ?0")
				.setParameter("0", studentid).uniqueResult()).intValue();
	}

	@Override
	public List<InterviewRecord> queryInterviewRecord(int page, int rows) {
		return sessionFactory.getCurrentSession()
				.createQuery("from InterviewRecord i where i.state > 3")
				.setFirstResult(rows * (page - 1)).setMaxResults(rows).list();
	}

	@Override
	public int getTotalNumberOfInterviewRecord() {
		return ((Long) sessionFactory
				.getCurrentSession()
				.createQuery(
						"select count(1) from InterviewRecord i where i.state > 3")
				.uniqueResult()).intValue();
	}

}
