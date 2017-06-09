package cn.gcs.employment.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.gcs.employment.service.SuccessEmploymentService;
import cn.gcs.employment.vo.SuccessEmploymentRecordItem;

import com.opensymphony.xwork2.ActionSupport;

@Component
@Scope("prototype")
public class SuccessEmploymentRecord extends ActionSupport {

	@Autowired
	private SuccessEmploymentService successEmploymentService;

	private int page;
	private int rows;

	private String fuzzy;

	private Integer id;
	private Integer studentid;
	private String name;
	private String school;
	private String education;
	private String company;
	private String job;
	private Date start;
	private Date end;

	private int total;
	private List<SuccessEmploymentRecordItem> result;

	public String execute() throws Exception {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("fuzzy", fuzzy);
		condition.put("id", id);
		condition.put("studentid", studentid);
		condition.put("name", name);
		condition.put("school", school);
		condition.put("education", education);
		condition.put("company", company);
		condition.put("job", job);
		condition.put("start", start);
		condition.put("end", end);
		result = successEmploymentService.getSuccessEmploymentRecord(page,
				rows, condition);
		total = successEmploymentService.getTotalNumberOfRecord(condition);
		return SUCCESS;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public void setFuzzy(String fuzzy) {
		this.fuzzy = fuzzy;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setStudentid(Integer studentid) {
		this.studentid = studentid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public int getTotal() {
		return total;
	}

	public List<SuccessEmploymentRecordItem> getRows() {
		return result;
	}

}
