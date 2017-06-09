package cn.gcs.course.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import cn.gcs.core.action.BaseAction;
import cn.gcs.core.util.QueryHelper;
import cn.gcs.course.entity.Course;
import cn.gcs.course.service.CourseService;
import cn.gcs.user.service.UserService;

import com.opensymphony.xwork2.ActionContext;

public class CourseAction extends BaseAction {

	@Resource
	private CourseService courseService;

	@Resource
	private UserService userService;

	private Course course;

	private Integer[] userIds;
	private Integer[] courseIds;

	private Map<String, Object> map = new HashMap<>();

	// 列表页面
	public String listUI() throws Exception {
		String roleName = "任课老师";
		ActionContext.getContext().getContextMap()
				.put("teacher", userService.findUserByRoleName(roleName));
		// 加载权限集合
		QueryHelper queryHelper = new QueryHelper(Course.class, "c");
		try {
			if (course != null) {
				if (StringUtils.isNotBlank(course.getName())) {
					queryHelper.addCondition("c.name like ?",
							"%" + course.getName() + "%");
				}
			}
			pageResult = courseService.getPageResult(queryHelper, getPage(),
					getRows());
			int total = (int) pageResult.getTotalCount();
			List<Course> list = pageResult.getItems();
			map.put("total", total);
			map.put("rows", list);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "listUI";
	}

	public String add() {
		try {
			if (course != null) {
				// 处理权限保存
				courseService.saveCourseAndTeacher(course, userIds);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	// 保存编辑
	public String edit() {
		try {
			if (course != null) {
				// 处理权限保存
				courseService.upadateCourseAndTeacher(course, userIds);
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
				courseService.delete(Integer.parseInt(id));
			}
		}
		return "list";
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
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

	public Integer[] getUserIds() {
		return userIds;
	}

	public void setUserIds(Integer[] userIds) {
		this.userIds = userIds;
	}
}
