package cn.gcs.employment.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.gcs.employment.dao.SuccessEmploymentDao;
import cn.gcs.employment.entity.SuccessEmployment;

@Component
public class SuccessEmploymentDaoImpl implements SuccessEmploymentDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<SuccessEmployment> queryEmploymentRecord(int page, int rows,
			Map<String, Object> condition) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(
				SuccessEmployment.class);
		c = processCondition(c, condition);
		c.setFirstResult(rows * (page - 1)).setMaxResults(rows);
		return c.list();
	}

	@Override
	public int getTotalNumberOfRecord(Map<String, Object> condition) {
		Criteria c = sessionFactory.getCurrentSession().createCriteria(
				SuccessEmployment.class);
		c = processCondition(c, condition);
		return c.list().size();
	}

	@Override
	public void insertRecord(SuccessEmployment successEmployment) {
		sessionFactory.getCurrentSession().save(successEmployment);
	}

	@Override
	public void updateRecord(SuccessEmployment successEmployment) {
		sessionFactory.getCurrentSession().update(successEmployment);
	}

	@Override
	public void deleteRecord(SuccessEmployment successEmployment) {
		sessionFactory.getCurrentSession().delete(successEmployment);
	}

	@Override
	public SuccessEmployment getById(int id) {
		return sessionFactory.getCurrentSession().get(SuccessEmployment.class,
				id);
	}

	private Criteria processCondition(Criteria c, Map<String, Object> co) {

		if (co.get("id") != null && !co.get("id").equals("")) {
			c.add(Restrictions.eq("successEmploymentId", co.get("id")));
		}
		if (co.get("studentid") != null && !co.get("studentid").equals("")) {
			c.createCriteria("student").add(
					Restrictions.eq("studentId", co.get("studentid")));
		}
		if (co.get("name") != null && !co.get("name").equals("")) {
			if (co.get("fuzzy").equals("true")) {
				c.createCriteria("student")
						.createCriteria("user")
						.add(Restrictions.ilike("name", co.get("name")
								.toString(), MatchMode.ANYWHERE));
			} else {
				c.createCriteria("student").createCriteria("user")
						.add(Restrictions.eq("name", co.get("name")));
			}

		}
		if (co.get("school") != null && !co.get("school").equals("")) {
			c.createCriteria("student").add(
					Restrictions.eq("graduated_university", co.get("school")));
		}
		if (co.get("education") != null && !co.get("education").equals("")) {
			c.createCriteria("student").add(
					Restrictions.eq("educational_background",
							co.get("education")));
		}
		if (co.get("company") != null && !co.get("company").equals("")) {
			if (co.get("fuzzy").equals("true")) {
				c.add(Restrictions.ilike("company", co.get("company")
						.toString(), MatchMode.ANYWHERE));
			} else {
				c.add(Restrictions.eq("company", co.get("company")));
			}
		}
		if (co.get("job") != null && !co.get("job").equals("")) {
			if (co.get("fuzzy").equals("true")) {
				c.add(Restrictions.ilike("job", co.get("job").toString(),
						MatchMode.ANYWHERE));
			} else {
				c.add(Restrictions.eq("job", co.get("job")));
			}
		}
		if (co.get("start") != null && !co.get("start").equals("")) {
			c.add(Restrictions.ge("date", co.get("start")));
		}
		if (co.get("end") != null && !co.get("end").equals("")) {
			c.add(Restrictions.le("date", co.get("end")));
		}
		return c;

	}
}
