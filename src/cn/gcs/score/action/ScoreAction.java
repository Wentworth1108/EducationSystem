package cn.gcs.score.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import cn.gcs.classinfo.entity.ClassInfo;
import cn.gcs.core.action.BaseAction;
import cn.gcs.core.util.QueryHelper;
import cn.gcs.exam.entity.Exam;
import cn.gcs.exam.service.ExamService;
import cn.gcs.score.entity.Score;
import cn.gcs.score.service.ScoreService;
import cn.gcs.student.entity.Student;
import cn.gcs.student.service.StudentService;

public class ScoreAction extends BaseAction{

	@Resource
	private ScoreService scoreService;
	
	@Resource
	private ExamService examService;
	
	@Resource
	private StudentService studentService;
	
	private Score score;
	
	private Integer examId;
	
	private String studentId;
	
	private Map<String, Object> map = new HashMap<>();
	
	public String listUI() throws Exception {
		ActionContext.getContext().getContextMap().put("examId", examService.findExamId());
		ActionContext.getContext().getContextMap().put("student", studentService.findObjects());
		// 加载权限集合
		QueryHelper queryHelper = new QueryHelper(Score.class, "s");
		try {
			if (score != null && score.getStudent() != null && score.getStudent().getUser() != null) {
				if (StringUtils.isNotBlank(score.getStudent().getUser().getName())) {
					queryHelper.addCondition("s.student.user.name like ?", "%" + score.getStudent().getUser().getName() + "%");
				}
			}
			pageResult = scoreService.getPageResult(queryHelper, getPage(), getRows());
			int total = (int) pageResult.getTotalCount();
			List<ClassInfo> list = pageResult.getItems();
			map.put("total",total);
			map.put("rows", list);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return SUCCESS;
	}

	public String add() {
		try {
			if (score != null) {
				Student student = studentService.getStuByStudent_id(studentId);
				scoreService.saveScore(score, examId, student);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
	
	// 保存编辑
	public String edit() {
		try {
			if (score != null) {
				Student student = studentService.getStuByStudent_id(studentId);
				score.setExam(new Exam(examId));
				score.setStudent(student);
				scoreService.update(score);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "list";
	}
	
	// 批量删除
	public String deleteSelected() {
		String[] selectedRow = ids.split(",");
		if (selectedRow != null) {
			for (String id : selectedRow) {
				scoreService.delete(Integer.parseInt(id));
			}
		}
		return "list";
	}
	
	public void verifyStudentId() {
		try {
			// 1.获取账号
			if (studentId != null) {
				// 2.根据账号到数据库中校验是否存在该账号对应的用户
				Student student = studentService.getStuByStudent_id(studentId);
				String strResult = "true";
				if (student == null) {
					strResult = "false";
				}
				// 输出
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html;charset=UTF-8");
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write(strResult.getBytes());
				outputStream.close();	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Score getScore() {
		return score;
	}
	public void setScore(Score score) {
		this.score = score;
	}

	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public Integer getExamId() {
		return examId;
	}
	public void setExamId(Integer examId) {
		this.examId = examId;
	}

	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	
	
}
