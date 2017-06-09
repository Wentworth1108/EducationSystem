package cn.gcs.interview.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.gcs.interview.entity.Interview;
import cn.gcs.interview.service.InterviewService;

import com.opensymphony.xwork2.ActionSupport;

@Component
@Scope("prototype")
public class InterviewAction extends ActionSupport {
	@Autowired
	private InterviewService interviewService;

	private int page;
	private int rows;

	private int total;
	private List<Interview> result;

	public String execute() throws Exception {

		result = interviewService.getInterview(page, rows);
		total = interviewService.getTotalNumberOfInterview();
		return SUCCESS;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public List<Interview> getRows() {
		return result;
	}
}
