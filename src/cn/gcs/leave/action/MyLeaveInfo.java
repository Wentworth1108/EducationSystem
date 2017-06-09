package cn.gcs.leave.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.gcs.leave.entity.LeaveRecord;
import cn.gcs.leave.service.LeaveService;

import com.opensymphony.xwork2.ActionSupport;

@Component
@Scope("prototype")
public class MyLeaveInfo extends ActionSupport {
	@Autowired
	private LeaveService leaveService;

	private List<LeaveRecord> leave;

	@Override
	public String execute() throws Exception {
		int userid = (int) ServletActionContext.getRequest().getSession()
				.getAttribute("id");
		this.leave = leaveService.getLeaveRecord(userid);
		return SUCCESS;
	}

	public List<LeaveRecord> getLeave() {
		return leave;
	}

}
