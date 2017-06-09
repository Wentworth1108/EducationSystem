package cn.gcs.rest.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gcs.core.dao.impl.BaseDaoImpl;
import cn.gcs.core.service.impl.BaseServiceImpl;
import cn.gcs.rest.dao.RestDao;
import cn.gcs.rest.entity.Rest;
import cn.gcs.rest.service.RestService;

@Service("restService")
@Transactional
public class RestServiceImpl extends BaseServiceImpl<Rest> implements RestService {
	
	private RestDao restDao;
	@Resource 
	public void setRestDao(RestDao restDao) {
		super.setBaseDao(restDao);
		this.restDao = restDao;
	}
}
