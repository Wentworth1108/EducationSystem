package cn.gcs.employment.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.gcs.employment.service.EmploymentService;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class ApprovalPutoff extends ActionSupport {
	@Autowired
	private EmploymentService employmentService;

	private String handle;
	private List<Integer> id;

	@Override
	public String execute() throws Exception {
		int userid = (int) ServletActionContext.getRequest().getSession()
				.getAttribute("id");
		employmentService.approvalPutoff(handle, id, userid);
		return NONE;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public void setId(List<Integer> id) {
		this.id = id;
	}
}
