package cn.gcs.violate.dao;

import java.util.List;
import java.util.Map;

import cn.gcs.core.dao.BaseDao;
import cn.gcs.violate.entity.Violate;

public interface ViolateDao extends BaseDao<Violate>{

	//通过类型查找对应次数
	int getCountByType(Integer type);

	//获取考勤
	List<Violate> getAttendance();
	
	
		
}
