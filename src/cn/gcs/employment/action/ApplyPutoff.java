package cn.gcs.employment.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.gcs.employment.service.EmploymentService;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class ApplyPutoff extends ActionSupport {
	@Autowired
	private EmploymentService employmentService;

	private Date leavedate;
	private Date backdate;
	private String reason;

	@Override
	public String execute() throws Exception {
		int studentid = (int) ServletActionContext.getRequest().getSession()
				.getAttribute("id");
		employmentService.applyPutoff(studentid, leavedate, backdate, reason);
		return NONE;
	}

	public void setLeavedate(Date leavedate) {
		this.leavedate = leavedate;
	}

	public void setBackdate(Date backdate) {
		this.backdate = backdate;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
