package cn.gcs.violate.action;

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
import cn.gcs.violate.attendance.Attendance;
import cn.gcs.violate.entity.Violate;
import cn.gcs.violate.service.ViolateService;
import cn.gcs.violate.vo.ViolateItem;

import com.opensymphony.xwork2.ActionContext;

public class ViolateAction extends BaseAction{
	@Resource
	private ViolateService violateService;
	
	@Resource
	private StudentService stuService;
	
	private Map<String, Object> map = new HashMap<>();
	
	private List<Violate> violateList;
	
	private List<Attendance> attendanceList;
	
	private Attendance attendance;
	
	private Violate violate;
	
	
	
	public String violateList() throws Exception {
		
		ActionContext.getContext().getContextMap().put("violateList", violateService.findObjects());
		QueryHelper queryHelper = new QueryHelper(Violate.class, "v");
		
		try {
			if (violate != null) {
				if (StringUtils.isNotBlank(violate.getStudent().getUser().getName())) {
					queryHelper.addCondition("v.student.user.name like ?", "%" + violate.getStudent().getUser().getName() + "%");
				}
			}
			pageResult = violateService.getPageResult(queryHelper, getPage(), getRows());
			int total = (int) pageResult.getTotalCount();
			List<Violate> list = pageResult.getItems();
			List<ViolateItem> result = new ArrayList<ViolateItem>();
			for(Violate v : list){
				ViolateItem temp = new ViolateItem(); 
				temp.setStudent_id(v.getStudent().getStudent_id());
				temp.setViolateId(v.getViolateId());
				temp.setRemark(v.getRemark());
				temp.setResult(v.getResult());
				temp.setViolate_date(v.getViolate_date());
				temp.setViolateItem(v.getViolateItem());
				temp.setMemo(v.getMemo());
				
				temp.setStuName(v.getStudent().getUser().getName());
				temp.setClassName(v.getStudent().getClassInfo().getName());
				
				result.add(temp);
				
				
			}
			map.put("total",total);
			map.put("rows", result);
		}catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return "violateList";
	}
	
			// 保存新增
			public String add() {
				try {
					if (violate != null) {
						String student_id = violate.getStudent().getStudent_id();
						System.out.println(student_id);
						//学生
						Student stu = stuService.getStuByStudent_id(student_id);
						stu.setDailyScore(stu.getDailyScore()-violate.getRemark());
						
						violate.setStudent(stu);
						violateService.save(violate);
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return SUCCESS;
			}
			
			
			public String attendanceList() throws Exception {
				ActionContext.getContext().getContextMap().put("attendanceList", violateService.getAttendance());
				QueryHelper queryHelper = new QueryHelper(Violate.class, "v");
				queryHelper.addCondition(" type>?", 0);
				try {
					if (violate != null) {
						if (StringUtils.isNotBlank(violate.getStudent().getUser().getName())) {
							queryHelper.addCondition("v.student.user.name like ?", "%" + violate.getStudent().getUser().getName() + "%");
						}
					}
					pageResult = violateService.getPageResult(queryHelper, getPage(), getRows());
					int total = (int) pageResult.getTotalCount();
					List<Violate> list = pageResult.getItems();
					List<Attendance> result = new ArrayList<Attendance>();
					
					for(Violate v : list){
						
						Attendance temp = new Attendance(); 
						
						temp.setName(v.getStudent().getUser().getName());
						temp.setClassName(v.getStudent().getClassInfo().getName());
						temp.setId(v.getViolateId());
						temp.setSchool(v.getStudent().getGraduated_university());
						
						if(v.getType()==1){
							temp.setAbsenteeism(1);
						}
						if(v.getType()==2){
							temp.setBeLate(1);
						}
						if(v.getType()==3){
							temp.setBeEarly(1);
						}
						temp.setDate(v.getViolate_date());
						result.add(temp);
						
						
					}
					map.put("total",total);
					map.put("rows", result);
				}catch (Exception e) {
					throw new Exception(e.getMessage());
				}
				return "attendanceList";
			}		
			
			
			// 批量删除
			public String deleteSelected() {
				String[] selectedRow = ids.split(",");     
				if (selectedRow != null) {
					for (String id : selectedRow) {
						Violate vio = violateService.findObjectById(Integer.parseInt(id));
						Student stu = stuService.findObjectById(vio.getStudent().getStudentId());
						stu.setDailyScore(stu.getDailyScore()+vio.getRemark());
						violateService.delete(Integer.parseInt(id));
					}
				}
				return SUCCESS;
			}
			
			// 校验学生学号存在
			public void verifyStudent() {
				try {
					// 1.获取账号
					if (violate != null && StringUtils.isNotBlank(violate.getStudent().getStudent_id())) {
						// 2.根据账号到数据库中校验是否存在该账号对应的用户
						List<Student> list = stuService.findUserByStuidAndId(violate.getStudent().getStudent_id(), violate.getStudent().getStudentId());
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
	
	public List<Attendance> getAttendanceList() {
		return attendanceList;
	}

	public void setAttendanceList(List<Attendance> attendanceList) {
		this.attendanceList = attendanceList;
	}

	public Attendance getAttendance() {
		return attendance;
	}

	public void setAttendance(Attendance attendance) {
		this.attendance = attendance;
	}

	public List<Violate> getList() {
		return violateList;
	}

	public void setList(List<Violate> violateList) {
		this.violateList = violateList;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public List<Violate> getViolateList() {
		return violateList;
	}

	public void setViolateList(List<Violate> violateList) {
		this.violateList = violateList;
	}

	public Violate getViolate() {
		return violate;
	}

	public void setViolate(Violate violate) {
		this.violate = violate;
	}
	
}
    