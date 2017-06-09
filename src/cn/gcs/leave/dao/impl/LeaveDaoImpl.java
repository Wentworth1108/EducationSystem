package cn.gcs.leave.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.gcs.leave.dao.LeaveDao;
import cn.gcs.leave.entity.LeaveRecord;

@Component
public class LeaveDaoImpl implements LeaveDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void insertRecord(LeaveRecord leave) {
		sessionFactory.getCurrentSession().save(leave);
	}

	@Override
	public List<LeaveRecord> getLeaveApply(int page, int rows, String role) {
		Query q = sessionFactory.getCurrentSession().createQuery(
				"from LeaveRecord l where l." + role + " = 0");
		q.setFirstResult(rows * (page - 1)).setMaxResults(rows);
		return q.list();
	}

	@Override
	public int getTotalNumberOfLeaveApply(String role) {
		return ((Long) sessionFactory
				.getCurrentSession()
				.createQuery(
						"select count(1) from LeaveRecord l where l." + role
								+ " = 0").uniqueResult()).intValue();
	}

	@Override
	public LeaveRecord getLeaveById(int id) {
		return sessionFactory.getCurrentSession().get(LeaveRecord.class, id);
	}

	@Override
	public void updateLeave(LeaveRecord leave) {
		sessionFactory.getCurrentSession().update(leave);
	}

	@Override
	public List<LeaveRecord> getLeaveRecord(int page, int rows) {
		Query q = sessionFactory
				.getCurrentSession()
				.createQuery(
						"from LeaveRecord");
		q.setFirstResult(rows * (page - 1)).setMaxResults(rows);
		return q.list();
	}

	@Override
	public int getTotalNumberOfLeaveRecord() {
		return ((Long) sessionFactory
				.getCurrentSession()
				.createQuery(
						"select count(1) from LeaveRecord")
				.uniqueResult()).intValue();
	}

	@Override
	public List<LeaveRecord> getLeaveRecord(int userid) {
		return sessionFactory.getCurrentSession()
				.createQuery("from LeaveRecord l where l.student.user.id = ?0")
				.setParameter("0", userid).list();
	}

}
