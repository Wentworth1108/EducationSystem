package cn.gcs.exam.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import cn.gcs.classinfo.service.ClassInfoService;
import cn.gcs.core.action.BaseAction;
import cn.gcs.core.util.QueryHelper;
import cn.gcs.course.service.CourseService;
import cn.gcs.exam.entity.Exam;
import cn.gcs.exam.entity.Exam;
import cn.gcs.exam.service.ExamService;

public class ExamAction extends BaseAction {

	@Resource
	private ExamService examService;
	
	@Resource
	private CourseService courseService;
	
	@Resource 
	private ClassInfoService classInfoService;
	
	private Exam exam;
	
	private Map<String, Object> map = new HashMap<>();
	
	// 列表页面
	public String listUI() throws Exception {
		ActionContext.getContext().getContextMap().put("course", courseService.findCourseNames());
		ActionContext.getContext().getContextMap().put("classInfo", classInfoService.findclassNames());
		// 加载权限集合
		QueryHelper queryHelper = new QueryHelper(Exam.class, "e");
		try {
			if (exam != null) {
				if (StringUtils.isNotBlank(exam.getClassName())) {
					queryHelper.addCondition("e.className like ?", "%" + exam.getClassName() + "%");
				}
			}
			pageResult = examService.getPageResult(queryHelper, getPage(), getRows());
			int total = (int) pageResult.getTotalCount();
			List<Exam> list = pageResult.getItems();
			map.put("total",total);
			map.put("rows", list);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return "listUI";
	}
	
	public String add() {
		try {
			if (exam != null) {
				examService.save(exam);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
		
	// 保存编辑
	public String edit() {
		try {
			if (exam != null) {	
				examService.update(exam);
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
				examService.delete(Integer.parseInt(id));
			}
		}
		return "list";
	}


	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

}
