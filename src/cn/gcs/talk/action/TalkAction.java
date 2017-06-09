package cn.gcs.talk.action;

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
import cn.gcs.student.entity.Student;
import cn.gcs.student.service.StudentService;
import cn.gcs.talk.entity.Talk;
import cn.gcs.talk.service.TalkService;
import cn.gcs.talk.vo.TalkItem;
import cn.gcs.user.entity.User;
import cn.gcs.user.service.UserService;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
public class TalkAction extends BaseAction{
	@Resource
	private TalkService talkService;
	
	private List<Talk> talkList;
	
	private Talk talk;
	
	@Resource
	private StudentService stuService;
	
	@Resource
	private UserService userService;
	
	private Map<String,Object> map = new HashMap<String,Object>();
	
	public String talkList() throws Exception{
		ActionContext.getContext().getContextMap().put("talkService",talkService.findObjects());
		QueryHelper queryHelper = new QueryHelper(Talk.class, "t");
		try {
			if (talk != null) {
				if (StringUtils.isNotBlank(talk.getStudent().getUser().getName())){
					queryHelper.addCondition("t.student.user.name like ?", "%" + talk.getStudent().getUser().getName() + "%");
				}
			}
			pageResult = talkService.getPageResult(queryHelper, getPage(), getRows());
			int total = (int) pageResult.getTotalCount();
			List<Talk> list = pageResult.getItems();
			List<TalkItem> result = new ArrayList<TalkItem>();
			for(Talk t : list){
				TalkItem temp = new TalkItem();
				temp.setTalkId(t.getTalkId());
				temp.setStuName(t.getStudent().getUser().getName());
				temp.setClassName(t.getStudent().getClassInfo().getName());
				temp.setTeaName(t.getUser().getName());
				temp.setStart_time(t.getStart_time());
				temp.setEnd_time(t.getEnd_time());
				temp.setDate(t.getDate());
				temp.setDescription(t.getDescription());
				temp.setStudent_id(t.getStudent().getStudent_id());
				
				result.add(temp);
			}
			map.put("total",total);
			map.put("rows", result);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "talkList";
	}

		// 保存新增
		public String add() {
			try {
				if (talk != null && StringUtils.isNotBlank(talk.getUser().getAccount())) {
					//导师用户
					List<User> teachers = userService.findUserByAccountAndId(talk.getUser().getAccount(), talk.getUser().getId());
					if (teachers != null && teachers.size()> 0){
						User teacher = teachers.get(0);
						System.out.println(teacher);
						talk.setUser(teacher);
					}
					//学生
					if(StringUtils.isNotBlank(talk.getStudent().getStudent_id())){
						Student stu = stuService.getStuByStudent_id(talk.getStudent().getStudent_id());
						talk.setStudent(stu);
					}
					talkService.save(talk);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return SUCCESS;
		}
		
		// 保存编辑
		public String edit() {
			try {
				if (talk != null) {
					
					//导师用户
					User teacher = userService.getUserByName(talk.getUser().getName());
					//学生
					Student stu = stuService.getStuByStudent_id(talk.getStudent().getStudent_id());
					talk.setUser(teacher);
					talk.setStudent(stu);
					
					talkService.update(talk);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return SUCCESS;
		}
		
		// 批量删除
		public String deleteSelected() {
			String[] selectedRow = ids.split(",");     
			if (selectedRow != null) {
				for (String id : selectedRow) {
					talkService.delete(Integer.parseInt(id));
				}
			}
			return SUCCESS;
		}
	

		// 校验导师账号存在
		public void verifyTeacher() {
			try {
				// 1.获取账号
				if (talk != null && StringUtils.isNotBlank(talk.getUser().getAccount())) {
					// 2.根据账号到数据库中校验是否存在该账号对应的用户
					List<User> list = userService.findUserByAccountAndId(talk.getUser().getAccount(), talk.getUser().getId());
					
					
					String strResult = "false";
					if (list != null && list.size()> 0 && list.get(0).getUserRoles() != null) {
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
		
		// 校验学生学号存在
		public void verifyStudent() {
			try {
				// 1.获取账号
				if (talk != null && StringUtils.isNotBlank(talk.getStudent().getStudent_id())) {
					// 2.根据账号到数据库中校验是否存在该账号对应的用户
					List<Student> list = stuService.findUserByStuidAndId(talk.getStudent().getStudent_id(), talk.getStudent().getStudentId());
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
	
	public List<Talk> getTalkList() {
		return talkList;
	}

	public void setTalkList(List<Talk> talkList) {
		this.talkList = talkList;
	}

	public Talk getTalk() {
		return talk;
	}

	public void setTalk(Talk talk) {
		this.talk = talk;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	
}
