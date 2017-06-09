package cn.gcs.interview.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.gcs.interview.service.InterviewService;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class Feedback extends ActionSupport {
	@Autowired
	private InterviewService interviewService;

	private Integer id;
	private String state;
	private String summary;

	@Override
	public String execute() throws Exception {
		interviewService.addFeedback(id, state, summary);
		return NONE;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
}
