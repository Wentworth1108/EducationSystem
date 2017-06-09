package cn.gcs.classinfo.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import cn.gcs.classinfo.entity.ClassInfo;
import cn.gcs.classinfo.service.ClassInfoService;
import cn.gcs.core.action.BaseAction;
import cn.gcs.core.util.QueryHelper;
import cn.gcs.course.service.CourseService;
import cn.gcs.user.entity.User;
import cn.gcs.user.service.UserService;

public class ClassInfoAction extends BaseAction {

	@Resource
	private ClassInfoService classInfoService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private CourseService courseService;
	
	private ClassInfo classInfo;
	
	private Integer userId;
	private Integer[] courseIds;
	
	private Map<String, Object> map = new HashMap<>();
	
	public String listUI() throws Exception {
		String roleName = "班主任";
		ActionContext.getContext().getContextMap().put("headTeacher", userService.findUserByRoleName(roleName));
		ActionContext.getContext().getContextMap().put("courseList", courseService.findObjects());
		// 加载权限集合
		QueryHelper queryHelper = new QueryHelper(ClassInfo.class, "c");
		try {
			if (classInfo != null) {
				if (StringUtils.isNotBlank(classInfo.getName())) {
					queryHelper.addCondition("c.name like ?", "%" + classInfo.getName() + "%");
				}
			}
			pageResult = classInfoService.getPageResult(queryHelper, getPage(), getRows());
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
			if (classInfo != null) {
				// 处理权限保存
				classInfoService.saveClassInfoAndCourseAndUser(classInfo, userId, courseIds);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}
	
	// 保存编辑
	public String edit() {
		try {
			if (classInfo != null) {
				classInfoService.updateClassInfoAndCourseAndUser(classInfo, userId, courseIds);
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
				classInfoService.delete(Integer.parseInt(id));
			}
		}
		return "list";
	}
	
	public ClassInfo getClassInfo() {
		return classInfo;
	}

	public void setClassInfo(ClassInfo classInfo) {
		this.classInfo = classInfo;
	}

	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public Integer[] getCourseIds() {
		return courseIds;
	}
	public void setCourseIds(Integer[] courseIds) {
		this.courseIds = courseIds;
	}

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
