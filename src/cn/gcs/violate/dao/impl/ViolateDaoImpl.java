package cn.gcs.violate.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import cn.gcs.core.dao.impl.BaseDaoImpl;
import cn.gcs.violate.dao.ViolateDao;
import cn.gcs.violate.entity.Violate;

public class ViolateDaoImpl extends BaseDaoImpl<Violate> implements ViolateDao {

	@Override
	public int getCountByType(Integer type) {
		Query query = currentSession().createQuery(
				"SELECT COUNT(violateId) FROM Violate WHERE type=?");
		query.setParameter(0, type);
		Long l = (Long) query.uniqueResult();
		int i = new Long(l).intValue();
		return i;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Violate> getAttendance() {
		Query query = currentSession().createQuery("FROM Violate WHERE type>0");
		return query.list();
	}



}
