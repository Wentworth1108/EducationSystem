package cn.gcs.violate.service;

import java.util.List;

import cn.gcs.core.service.BaseService;
import cn.gcs.violate.entity.Violate;

public interface ViolateService extends BaseService<Violate> {

	//通过类型查找对应次数
	int getCountByType(Integer type);

	//获取考勤
	List<Violate> getAttendance();

}
