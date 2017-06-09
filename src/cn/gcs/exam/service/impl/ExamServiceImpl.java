package cn.gcs.exam.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gcs.core.service.impl.BaseServiceImpl;
import cn.gcs.exam.dao.ExamDao;
import cn.gcs.exam.entity.Exam;
import cn.gcs.exam.service.ExamService;

@Service("examService")
@Transactional
public class ExamServiceImpl extends BaseServiceImpl<Exam> implements ExamService {
	
	private ExamDao examDao;
	
	@Resource
	public void setExamDao(ExamDao examDao) {
		super.setBaseDao(examDao);
		this.examDao = examDao;
	}

	@Override
	public List<Integer> findExamId() {
		return examDao.findExamId();
	}

}
