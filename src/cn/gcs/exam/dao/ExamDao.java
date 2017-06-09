package cn.gcs.exam.dao;

import java.util.List;

import cn.gcs.core.dao.BaseDao;
import cn.gcs.exam.entity.Exam;

public interface ExamDao extends BaseDao<Exam> {

	// 查询考试Id
	List<Integer> findExamId();

}
