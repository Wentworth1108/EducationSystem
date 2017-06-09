package cn.gcs.employment.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.gcs.employment.entity.GiveupEmployment;
import cn.gcs.employment.service.EmploymentService;

import com.opensymphony.xwork2.ActionSupport;

@Component
@Scope("prototype")
public class MyGiveupRecord extends ActionSupport {
	@Autowired
	private EmploymentService employmentService;

	private List<GiveupEmployment> giveup;

	@Override
	public String execute() throws Exception {
		int userid = (int) ServletActionContext.getRequest().getSession()
				.getAttribute("id");
		this.giveup = employmentService.getGiveupRecord(userid);
		return SUCCESS;
	}

	public List<GiveupEmployment> getGiveup() {
		return giveup;
	}

}
