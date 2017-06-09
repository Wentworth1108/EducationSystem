package cn.gcs.core.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.gcs.core.dao.BaseDao;
import cn.gcs.core.page.PageResult;
import cn.gcs.core.service.BaseService;
import cn.gcs.core.util.QueryHelper;

@Transactional
public class BaseServiceImpl<T> implements BaseService<T> {

	private BaseDao<T> baseDao;
	
	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}
	
	@Override
	public void save(T entity) {
		baseDao.save(entity);
	}

	@Override
	public void update(T entity) {
		baseDao.update(entity);
	}

	@Override
	public void delete(Serializable id) {
		baseDao.delete(id);
	}

	@Override
	public T findObjectById(Serializable id) {
		return baseDao.findObjectById(id);
	}

	@Override
	public List<T> findObjects() {
		return baseDao.findObjects();
	}
	

	@Override
	public List<T> findObjects(QueryHelper queryHelper) {
		return baseDao.findObjects(queryHelper);
	}

	@Override
	public PageResult getPageResult(QueryHelper queryHelper, int page, int rows) {
		return baseDao.getPageResult(queryHelper, page, rows);
	}
	


}
