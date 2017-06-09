package cn.gcs.leave.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.gcs.leave.service.LeaveService;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class ApprovalLeave extends ActionSupport {
	@Autowired
	private LeaveService leaveService;

	private String handle;
	private List<Integer> id;

	@Override
	public String execute() throws Exception {
		int userid = (int) ServletActionContext.getRequest().getSession()
				.getAttribute("id");
		leaveService.approvalLeave(handle, id, userid);
		return NONE;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public void setId(List<Integer> id) {
		this.id = id;
	}
}
