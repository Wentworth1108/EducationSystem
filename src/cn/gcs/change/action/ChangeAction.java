package cn.gcs.change.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import cn.gcs.change.entity.Change;
import cn.gcs.change.service.ChangeService;
import cn.gcs.change.vo.ChangeItem;
import cn.gcs.classinfo.entity.ClassInfo;
import cn.gcs.classinfo.service.ClassInfoService;
import cn.gcs.core.action.BaseAction;
import cn.gcs.core.util.QueryHelper;
import cn.gcs.student.entity.Student;
import cn.gcs.student.service.StudentService;
import cn.gcs.user.entity.User;

import com.opensymphony.xwork2.ActionContext;

public class ChangeAction extends BaseAction{
	@Resource
	private ChangeService changeService;
	
	@Resource
	private StudentService studentService;
	
	@Resource
	private ClassInfoService classInfoService;
	
	
	private List<Change> changeList;
	
	private Change change;
	
	private Integer classId;
	private Integer newClassId;
	
	private Integer studentId;
	
	private Map<String,Object> map = new HashMap<String,Object>();
	
	
	
	
	public String changeList() throws Exception{
		ActionContext.getContext().getContextMap().put("classInfo", classInfoService.findObjects());
		ActionContext.getContext().getContextMap().put("changeList", changeService.findObjects());
		QueryHelper queryHelper = new QueryHelper(Change.class, "c");
		try {
			if (change != null) {
				if (StringUtils.isNotBlank(change.getStudent().getUser().getName())){
					queryHelper.addCondition("c.student.user.name like ?", "%" + change.getStudent().getUser().getName() + "%");
				}
			}
			pageResult = changeService.getPageResult(queryHelper, getPage(), getRows());
			int total = (int) pageResult.getTotalCount();
			List<Change> list = pageResult.getItems();
			List<ChangeItem> result = new ArrayList<ChangeItem>();
			
			for(Change c : list){
				
				ChangeItem temp = new ChangeItem();
				
				temp.setChangeId(c.getChangeId());
				temp.setStudent_id(c.getStudent().getStudent_id());
				temp.setStudentId(c.getStudent().getStudentId());
				temp.setClassId(c.getClassInfo().getClassId());
				temp.setName(c.getStudent().getUser().getName());
				temp.setSchool(c.getStudent().getGraduated_university());
				temp.setClassName(c.getStudent().getClassInfo().getName());
				temp.setNewClass(c.getClassInfo().getName());
				temp.setDate(c.getDate());
				temp.setReason(c.getReason());
				temp.setApprove(c.getApprove());
				
				
				result.add(temp);
			}
			map.put("total",total);
			map.put("rows", result);
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "changeList";
	}
	
	public String changeInfo() throws Exception{
		QueryHelper queryHelper = new QueryHelper(Change.class, "c");
		Map<String,Object> session = ActionContext.getContext().getSession();
		Integer id = (Integer) session.get("id");
		try {
			queryHelper.addCondition("c.student.user.id=?", id);
			pageResult = changeService.getPageResult(queryHelper, getPage(), getRows());
			int total = (int) pageResult.getTotalCount();
			List<Change> list = pageResult.getItems();
			List<ChangeItem> result = new ArrayList<ChangeItem>();
			
			for(Change c : list){
				
				ChangeItem temp = new ChangeItem();
				
				temp.setChangeId(c.getChangeId());
				temp.setStudent_id(c.getStudent().getStudent_id());
				temp.setStudentId(c.getStudent().getStudentId());
				temp.setClassId(c.getClassInfo().getClassId());
				temp.setName(c.getStudent().getUser().getName());
				temp.setSchool(c.getStudent().getGraduated_university());
				temp.setClassName(c.getStudent().getClassInfo().getName());
				temp.setNewClass(c.getClassInfo().getName());
				temp.setDate(c.getDate());
				temp.setReason(c.getReason());
				temp.setApprove(c.getApprove());
				
				
				result.add(temp);
			}
			map.put("total",total);
			map.put("rows", result);
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "changeInfo";
	}
	
	
			// 保存新增
			public String add() {
				try {
					if (change != null) {
						//学生
						Student stu = studentService.getStuByStudent_id(change.getStudent().getStudent_id());
						//新班级
						ClassInfo classInfo = classInfoService.getClassByName(change.getClassInfo().getName());
						change.setClassInfo(classInfo);
						change.setStudent(stu);
						change.setApprove("0");
						changeService.save(change);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return SUCCESS;
			}
	
			// 保存编辑
			public String edit() {
				try {
					if (change != null) {
						//学生
						Student stu = studentService.getStuByStudent_id(change.getStudent().getStudent_id());
						//新班级
						ClassInfo classInfo = classInfoService.getClassByName(change.getClassInfo().getName());
						change.setStudent(stu);
						change.setClassInfo(classInfo);
						System.out.println(change.getChangeId());
						changeService.update(change);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return SUCCESS;
			}
	
		public String editClass() {
			if (change != null) {
				ClassInfo classInfo = classInfoService.findObjectById(classId);
				change.setClassInfo(classInfoService.findObjectById(newClassId));
				change.setStudent(studentService.findObjectById(studentId));
				change.getStudent().setClassInfo(classInfo);
				changeService.update(change);
			}
			return "list";
		}
		
		// 校验学生学号存在
		public void verifyStudent() {
			try {
				// 1.获取账号
				if (change != null && StringUtils.isNotBlank(change.getStudent().getStudent_id())) {
					// 2.根据账号到数据库中校验是否存在该账号对应的用户
					List<Student> list = studentService.findUserByStuidAndId(change.getStudent().getStudent_id(), change.getStudent().getStudentId());
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
	

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public List<Change> getChangeList() {
		return changeList;
	}

	public void setChangeList(List<Change> changeList) {
		this.changeList = changeList;
	}

	public Change getChange() {
		return change;
	}

	public void setChange(Change change) {
		this.change = change;
	}


	public Integer getClassId() {
		return classId;
	}


	public void setClassId(Integer classId) {
		this.classId = classId;
	}


	public Integer getNewClassId() {
		return newClassId;
	}


	public void setNewClassId(Integer newClassId) {
		this.newClassId = newClassId;
	}


	public Integer getStudentId() {
		return studentId;
	}


	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	
}
