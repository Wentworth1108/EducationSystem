package cn.gcs.employment.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.gcs.employment.entity.PutoffEmployment;
import cn.gcs.employment.service.EmploymentService;

import com.opensymphony.xwork2.ActionSupport;

@Component
@Scope("prototype")
public class MyPutoffRecord extends ActionSupport {
	@Autowired
	private EmploymentService employmentService;

	private List<PutoffEmployment> putoff;

	@Override
	public String execute() throws Exception {
		int userid = (int) ServletActionContext.getRequest().getSession()
				.getAttribute("id");
		this.putoff = employmentService.getPutoffRecord(userid);
		return SUCCESS;
	}

	public List<PutoffEmployment> getPutoff() {
		return putoff;
	}

}
