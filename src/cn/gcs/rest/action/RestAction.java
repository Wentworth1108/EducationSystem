package cn.gcs.rest.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import cn.gcs.core.action.BaseAction;
import cn.gcs.core.util.QueryHelper;
import cn.gcs.rest.entity.Rest;
import cn.gcs.rest.service.RestService;
import cn.gcs.rest.vo.RestItem;
import cn.gcs.student.entity.Student;
import cn.gcs.student.service.StudentService;
import cn.gcs.user.entity.User;
import cn.gcs.user.service.UserService;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
public class RestAction extends BaseAction {
	@Resource
	private RestService restService;
	
	@Resource
	private StudentService stuService;
	
	@Resource
	private UserService userService;
	
	private Rest rest; 
	
	private List<Rest> restList;
	
	private Map<String, Object> map = new HashMap<>();
	 
	public String stuRestList() throws Exception{
		QueryHelper queryHelper = new QueryHelper(Rest.class, "r");
		Map<String,Object> session = ActionContext.getContext().getSession();
		Integer id = (Integer) session.get("id");
		try {
			queryHelper.addCondition("r.student.user.id=?", id);
			pageResult = restService.getPageResult(queryHelper, getPage(), getRows());
			int total = (int) pageResult.getTotalCount();
			List<Rest> list = pageResult.getItems();
			List<RestItem> result = new ArrayList<RestItem>();
			
			for(Rest r : list){
				RestItem temp = new RestItem();
				
				temp.setRestId(r.getRestId());
				temp.setName(r.getStudent().getUser().getName());
				temp.setClassName(r.getStudent().getClassInfo().getName());
				temp.setStart_time(r.getStart_time());
				temp.setEnd_time(r.getEnd_time());
				temp.setReason(r.getReason());
				temp.setState(r.getState());
				temp.setUserName(r.getUser().getName());
				temp.setDays(r.getStart_time(),r.getEnd_time());
				
				result.add(temp);
			}
			map.put("total",total);
			map.put("rows", result);
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "stuRestList";
	}
	
	
	public String restList() throws Exception {
		ActionContext.getContext().getContextMap().put("restList", restService.findObjects());
		QueryHelper queryHelper = new QueryHelper(Rest.class, "r");
		try {
			if (rest != null) {
				if (StringUtils.isNotBlank(rest.getStudent().getUser().getName())) {
					queryHelper.addCondition("r.student.user.name like ?", "%" + rest.getStudent().getUser().getName() + "%");
				}
			}
			pageResult = restService.getPageResult(queryHelper, getPage(), getRows());
			int total = (int) pageResult.getTotalCount();
			List<Rest> list = pageResult.getItems();
			List<RestItem> result = new ArrayList<RestItem>();
			
			for(Rest r : list){
				RestItem temp = new RestItem();
				
				temp.setRestId(r.getRestId());
				temp.setName(r.getStudent().getUser().getName());
				temp.setClassName(r.getStudent().getClassInfo().getName());
				temp.setStart_time(r.getStart_time());
				temp.setEnd_time(r.getEnd_time());
				temp.setReason(r.getReason());
				temp.setState(r.getState());
				temp.setUserName(r.getUser().getName());
				temp.setDays(r.getStart_time(),r.getEnd_time());
				temp.setStudent_id(r.getStudent().getStudent_id());
				
				result.add(temp);
			}
			map.put("total",total);
			map.put("rows", result);
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "restList";
	}
	
	// 保存新增
			public String add() {
				try {
					if (rest != null) {
						//学生
						Student stu = stuService.getStuByStudent_id(rest.getStudent().getStudent_id());
						//导师 
						User user = userService.getUserByName(rest.getUser().getName());
						rest.setUser(user);
						rest.setStudent(stu);
						rest.setState("0");
						restService.save(rest);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return SUCCESS;
			}
	
			// 保存编辑
			public String edit() {
				try {
					if (rest != null) {
						//导师用户
						User teacher = userService.getUserByName(rest.getUser().getName());
						//学生
						Student stu = stuService.getStuByStudent_id(rest.getStudent().getStudent_id());
						rest.setUser(teacher);
						rest.setStudent(stu);
						
						restService.update(rest);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return SUCCESS;
			}
			
			// 校验学生学号存在
			public void verifyStudent() {
				try {
					// 1.获取账号
					if (rest != null && StringUtils.isNotBlank(rest.getStudent().getStudent_id())) {
						// 2.根据账号到数据库中校验是否存在该账号对应的用户
						List<Student> list = stuService.findUserByStuidAndId(rest.getStudent().getStudent_id(), rest.getStudent().getStudentId());
						String strResult = "false";
						if (list != null && list.size()> 0) {
							// 说明该账号已存在
							strResult = "true";
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
				
			
	public Rest getRest() {
		return rest;
	}
	public void setRest(Rest rest) {
		this.rest = rest;
	}
	public List<Rest> getRestList() {
		return restList;
	}
	public void setRestList(List<Rest> restList) {
		this.restList = restList;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
}
