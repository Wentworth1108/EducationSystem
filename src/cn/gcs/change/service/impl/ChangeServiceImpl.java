package cn.gcs.change.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gcs.change.dao.ChangeDao;
import cn.gcs.change.entity.Change;
import cn.gcs.change.service.ChangeService;
import cn.gcs.core.page.PageResult;
import cn.gcs.core.service.impl.BaseServiceImpl;
import cn.gcs.core.util.QueryHelper;

@Service("changeService")
@Transactional
public class ChangeServiceImpl extends BaseServiceImpl<Change> implements ChangeService {
	
	private ChangeDao changeDao;
	@Resource
	public void setChangeDao(ChangeDao changeDao) {
		super.setBaseDao(changeDao);
		this.changeDao = changeDao;
	}
	
	
	


}
