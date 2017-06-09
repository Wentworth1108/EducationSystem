package cn.gcs.user.action;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.gcs.user.entity.User;
import cn.gcs.user.service.UserService;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class Login extends ActionSupport {

	@Autowired
	private UserService userService;

	private String username;
	private String password;

	@Override
	public String execute() throws Exception {
		User user = userService
				.findUserByAccountAndPassword(username, password);
		if (user != null) {
			ServletActionContext.getRequest().getSession()
					.setAttribute("id", user.getId());
			ServletActionContext.getRequest().getSession()
					.setAttribute("name", user.getName());
			if (user.getUserRoles() != null && user.getUserRoles().size() > 0) {
				ServletActionContext.getResponse().getWriter()
						.print("{\"mode\":1,\"type\":1}");
			} else {
				ServletActionContext.getResponse().getWriter()
						.print("{\"mode\":1,\"type\":2}");
			}
		} else {
			ServletActionContext.getResponse().getWriter()
					.print("{\"mode\"：2}");
		}
		return NONE;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
