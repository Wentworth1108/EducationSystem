package cn.gcs.user.action;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class Logout extends ActionSupport {

	@Override
	public String execute() throws Exception {
		ServletActionContext.getRequest().getSession().removeAttribute("id");
		ServletActionContext.getRequest().getSession().removeAttribute("name");
		return SUCCESS;
	}
}
