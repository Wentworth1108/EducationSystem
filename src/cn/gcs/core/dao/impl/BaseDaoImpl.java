package cn.gcs.core.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.gcs.core.dao.BaseDao;
import cn.gcs.core.page.PageResult;
import cn.gcs.core.util.QueryHelper;

public abstract class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	
	Class<T> clazz;
	
	@Resource
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass(); //BaseDaoImpl<T>
		clazz = (Class<T>) pt.getActualTypeArguments()[0];
	}
	
	@Override
	public void save(T entity) {
		currentSession().save(entity);
	}

	@Override
	public void update(T entity) {
		currentSession().update(entity);
	}

	@Override
	public void delete(Serializable id) {
		T t = findObjectById(id);
		getHibernateTemplate().delete(t);
	}

	@Override
	public T findObjectById(Serializable id) {
		T t = sessionFactory.getCurrentSession().get(clazz, id);
		return t;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findObjects() {
		Query query = currentSession().createQuery("FROM " + clazz.getSimpleName());
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findObjects(QueryHelper queryHelper) {
		Query query = sessionFactory.getCurrentSession().createQuery(queryHelper.getQueryListHql());
		List<Object> parameters = queryHelper.getParameters();
		if (parameters != null) {
			for (int i = 0; i < parameters.size(); i++) {
				query.setParameter(i, parameters.get(i));
			}
		}
		return query.list();
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public PageResult getPageResult(QueryHelper queryHelper, int page, int rows) {
		Query query = currentSession().createQuery(queryHelper.getQueryListHql());
		List<Object> parameters = queryHelper.getParameters();
		if (parameters != null) {
			for (int i = 0; i < parameters.size(); i++) {
				query.setParameter(i, parameters.get(i));
			}
		}
		if (page < 1) {
			page = 1;
		}
		
		query.setFirstResult((page - 1) * rows); // 设置数据起始索引号
		query.setMaxResults(rows);
		List items = query.list(); 

		// 获取总记录数
		Query queryCount = currentSession().createQuery(queryHelper.getQueryCountHql());
		if (parameters != null) {
			for (int i = 0; i < parameters.size(); i++) {
				queryCount.setParameter(i, parameters.get(i));
			}
		}
		long totalCount = (Long) queryCount.uniqueResult(); 
		
		return new PageResult(totalCount, page, rows, items);
	}
	
}
