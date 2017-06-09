package cn.gcs.employment.action;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.gcs.employment.service.EmploymentService;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class ApplyGiveup extends ActionSupport {
	@Autowired
	private EmploymentService employmentService;

	private String reason;

	@Override
	public String execute() throws Exception {
		int studentid = (int) ServletActionContext.getRequest().getSession()
				.getAttribute("id");
		employmentService.applyGiveup(studentid, reason);
		return NONE;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
