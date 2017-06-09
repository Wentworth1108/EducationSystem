package cn.gcs.exam.service;

import java.util.List;

import cn.gcs.core.service.BaseService;
import cn.gcs.exam.entity.Exam;

public interface ExamService extends BaseService<Exam> {

	// 操作考试id
	List<Integer> findExamId();

}
