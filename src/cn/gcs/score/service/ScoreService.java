package cn.gcs.score.service;

import java.util.List;

import cn.gcs.core.service.BaseService;
import cn.gcs.score.entity.Score;
import cn.gcs.student.entity.Student;

public interface ScoreService extends BaseService<Score> {

	// 保存学生考试成绩
	void saveScore(Score score, Integer examId, Student student);

	// 根据学生id查找成绩
//	List<Score> findScoresByStudentId();

}
