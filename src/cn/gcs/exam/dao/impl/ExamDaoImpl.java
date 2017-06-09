package cn.gcs.exam.dao.impl;

import java.util.List;

import org.hibernate.Query;

import cn.gcs.core.dao.impl.BaseDaoImpl;
import cn.gcs.exam.dao.ExamDao;
import cn.gcs.exam.entity.Exam;

public class ExamDaoImpl extends BaseDaoImpl<Exam> implements ExamDao {

	@Override
	public List<Integer> findExamId() {
		Query query = getSessionFactory().getCurrentSession().createQuery("SELECT examId FROM Exam");
		
		return query.list();
	}


}
