package cn.gcs.interview.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.gcs.interview.service.InterviewService;
import cn.gcs.interview.vo.InterviewRecordItem;

import com.opensymphony.xwork2.ActionSupport;

@Component
@Scope("prototype")
public class ApprovalInterviewList extends ActionSupport {
	@Autowired
	private InterviewService interviewService;

	private int page;
	private int rows;

	private int total;
	private List<InterviewRecordItem> result;

	public String execute() throws Exception {

		result = interviewService.getInterviewApply(page, rows);
		total = interviewService.getTotalNumberOfInterviewApply();
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

	public List<InterviewRecordItem> getRows() {
		return result;
	}
}
