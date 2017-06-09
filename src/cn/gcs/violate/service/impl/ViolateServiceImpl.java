package cn.gcs.violate.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gcs.core.service.impl.BaseServiceImpl;
import cn.gcs.violate.dao.ViolateDao;
import cn.gcs.violate.entity.Violate;
import cn.gcs.violate.service.ViolateService;

@Service("violateService")
@Transactional
public class ViolateServiceImpl extends BaseServiceImpl<Violate> implements
		ViolateService {

	private ViolateDao violateDao;

	@Resource
	public void setViolateDao(ViolateDao violateDao) {
		super.setBaseDao(violateDao);
		this.violateDao = violateDao;
	}

	@Override
	public int getCountByType(Integer type) {
		return violateDao.getCountByType(type);
	}

	@Override
	public List<Violate> getAttendance() {
		return violateDao.getAttendance();
	}

}
