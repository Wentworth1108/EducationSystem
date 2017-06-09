package cn.gcs.interview.action;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.gcs.interview.service.InterviewService;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class ApplyInterview extends ActionSupport {
	@Autowired
	private InterviewService interviewService;

	private Integer interviewid;

	@Override
	public String execute() throws Exception {
		int userid = (int) ServletActionContext.getRequest().getSession()
				.getAttribute("id");
		interviewService.addInterviewApply(userid, interviewid);
		return NONE;
	}

	public void setInterviewid(Integer interviewid) {
		this.interviewid = interviewid;
	}
}
