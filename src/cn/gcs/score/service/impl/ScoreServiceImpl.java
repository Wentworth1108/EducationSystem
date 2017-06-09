package cn.gcs.score.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.gcs.core.service.impl.BaseServiceImpl;
import cn.gcs.exam.entity.Exam;
import cn.gcs.score.dao.ScoreDao;
import cn.gcs.score.entity.Score;
import cn.gcs.score.service.ScoreService;
import cn.gcs.student.entity.Student;

@Service("scoreService")
@Transactional
public class ScoreServiceImpl extends BaseServiceImpl<Score> implements ScoreService {

	private ScoreDao scoreDao;
	
	@Resource
	public void setScoreDao(ScoreDao scoreDao) {
		super.setBaseDao(scoreDao);
		this.scoreDao = scoreDao;
	}

	@Override
	public void saveScore(Score score, Integer examId, Student student) {
		// 1.保存成绩
		save(score);
		// 2.保存学生、考试
		if (student != null) {
			score.setStudent(student);
		}
		if (examId != null) {
			score.setExam(new Exam(examId));
		}
	}

}
