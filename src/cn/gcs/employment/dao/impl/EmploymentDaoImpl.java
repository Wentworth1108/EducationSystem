package cn.gcs.employment.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.gcs.employment.dao.EmploymentDao;
import cn.gcs.employment.entity.GiveupEmployment;
import cn.gcs.employment.entity.PutoffEmployment;

@Component
public class EmploymentDaoImpl implements EmploymentDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void insertPutoffRecord(PutoffEmployment putoffEmployment) {
		sessionFactory.getCurrentSession().save(putoffEmployment);
	}

	@Override
	public void insertGiveupRecord(GiveupEmployment giveupEmployment) {
		sessionFactory.getCurrentSession().save(giveupEmployment);
	}

	@Override
	public List<PutoffEmployment> getPutoffApply(int page, int rows, String role) {
		Query q = sessionFactory.getCurrentSession().createQuery(
				"from PutoffEmployment p where p." + role + " = 0");
		q.setFirstResult(rows * (page - 1)).setMaxResults(rows);
		return q.list();
	}

	@Override
	public List<GiveupEmployment> getGiveupApply(int page, int rows, String role) {
		Query q = sessionFactory.getCurrentSession().createQuery(
				"from GiveupEmployment g where g." + role + " = 0");
		q.setFirstResult(rows * (page - 1)).setMaxResults(rows);
		return q.list();
	}

	@Override
	public int getTotalNumberOfPutoffApply(String role) {
		return ((Long) sessionFactory
				.getCurrentSession()
				.createQuery(
						"select count(1) from PutoffEmployment p where p."
								+ role + " = 0").uniqueResult()).intValue();
	}

	@Override
	public int getTotalNumberOfGiveupApply(String role) {
		return ((Long) sessionFactory
				.getCurrentSession()
				.createQuery(
						"select count(1) from GiveupEmployment g where g."
								+ role + " = 0").uniqueResult()).intValue();
	}

	@Override
	public PutoffEmployment getPutoffById(int id) {
		return sessionFactory.getCurrentSession().get(PutoffEmployment.class,
				id);
	}

	@Override
	public GiveupEmployment getGiveupById(int id) {
		return sessionFactory.getCurrentSession().get(GiveupEmployment.class,
				id);
	}

	@Override
	public void updatePutoff(PutoffEmployment putoffEmployment) {
		sessionFactory.getCurrentSession().update(putoffEmployment);
	}

	@Override
	public void updateGiveup(GiveupEmployment giveupEmployment) {
		sessionFactory.getCurrentSession().update(giveupEmployment);
	}

	@Override
	public List<PutoffEmployment> getPutoffRecord(int page, int rows) {
		Query q = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from PutoffEmployment");
		q.setFirstResult(rows * (page - 1)).setMaxResults(rows);
		return q.list();
	}

	@Override
	public List<GiveupEmployment> getGiveupRecord(int page, int rows) {
		Query q = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from GiveupEmployment");
		q.setFirstResult(rows * (page - 1)).setMaxResults(rows);
		return q.list();
	}

	@Override
	public int getTotalNumberOfPutoffRecord() {
		return ((Long) sessionFactory
				.getCurrentSession()
				.createQuery(
						"select count(1) from PutoffEmployment")
				.uniqueResult()).intValue();
	}

	@Override
	public int getTotalNumberOfGiveupRecord() {
		return ((Long) sessionFactory
				.getCurrentSession()
				.createQuery(
						"select count(1) from GiveupEmployment")
				.uniqueResult()).intValue();
	}

	@Override
	public List<PutoffEmployment> getPutoffRecord(int userid) {
		return sessionFactory
				.getCurrentSession()
				.createQuery(
						"from PutoffEmployment p where p.student.user.id = ?0")
				.setParameter("0", userid).list();
	}

	@Override
	public List<GiveupEmployment> getGiveupRecord(int userid) {
		return sessionFactory
				.getCurrentSession()
				.createQuery(
						"from GiveupEmployment g where g.student.user.id = ?0")
				.setParameter("0", userid).list();
	}

}
