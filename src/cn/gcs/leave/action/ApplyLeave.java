package cn.gcs.leave.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.gcs.leave.service.LeaveService;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class ApplyLeave extends ActionSupport {
	@Autowired
	private LeaveService leaveService;

	private Date date;
	private String reason;

	@Override
	public String execute() throws Exception {
		int studentid = (int) ServletActionContext.getRequest().getSession()
				.getAttribute("id");
		System.out.println(studentid);
		leaveService.applyLeave(studentid, date, reason);
		return NONE;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
}
