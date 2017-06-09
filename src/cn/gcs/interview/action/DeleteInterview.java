package cn.gcs.interview.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.gcs.interview.service.InterviewService;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class DeleteInterview extends ActionSupport {

	@Autowired
	private InterviewService interviewService;

	private List<Integer> id;

	@Override
	public String execute() throws Exception {
		interviewService.removeInterview(id);
		return NONE;
	}

	public void setId(List<Integer> id) {
		this.id = id;
	}
}
