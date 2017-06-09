package cn.gcs.core.service;

import java.io.Serializable;
import java.util.List;

import cn.gcs.core.page.PageResult;
import cn.gcs.core.util.QueryHelper;

public interface BaseService<T> {
	
	public void save(T entity);
	
	public void update(T entity);
	
	public void delete(Serializable id);
	
	public T findObjectById(Serializable id);
	
	public List<T> findObjects();
	
	//条件查询实体列表--查询助手queryHelper 
	public List<T> findObjects(QueryHelper queryHelper);
	//分页条件查询实体列表
	public PageResult getPageResult(QueryHelper queryHelper, int page, int rows);
}
