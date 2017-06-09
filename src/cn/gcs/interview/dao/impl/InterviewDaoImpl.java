package cn.gcs.interview.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.gcs.interview.dao.InterviewDao;
import cn.gcs.interview.entity.Interview;

@Component
public class InterviewDaoImpl implements InterviewDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Interview> queryInterview(int page, int rows) {
		return sessionFactory.getCurrentSession()
				.createQuery("from Interview i where i.failTime > ?0")
				.setParameter("0", new Date())
				.setFirstResult(rows * (page - 1)).setMaxResults(rows).list();
	}

	@Override
	public int getTotalNumberOfInterview() {
		return ((Long) sessionFactory
				.getCurrentSession()
				.createQuery(
						"select count(1) from Interview i where i.failTime > ?0")
				.setParameter("0", new Date()).uniqueResult()).intValue();
	}

	@Override
	public void insertInterview(Interview interview) {
		sessionFactory.getCurrentSession().save(interview);
	}

	@Override
	public void updateInterview(Interview interview) {
		sessionFactory.getCurrentSession().update(interview);
	}

	@Override
	public void deleteInterview(Interview interview) {
		sessionFactory.getCurrentSession().delete(interview);
	}

	@Override
	public Interview getById(int id) {
		return sessionFactory.getCurrentSession().get(Interview.class, id);
	}

	@Override
	public List<Interview> queryOldInterview(int page, int rows) {
		return sessionFactory.getCurrentSession()
				.createQuery("from Interview i where i.failTime <= ?0")
				.setParameter("0", new Date())
				.setFirstResult(rows * (page - 1)).setMaxResults(rows).list();
	}

	@Override
	public int getTotalNumberOfOldInterview() {
		return ((Long) sessionFactory
				.getCurrentSession()
				.createQuery(
						"select count(1) from Interview i where i.failTime <= ?0")
				.setParameter("0", new Date()).uniqueResult()).intValue();
	}

}
