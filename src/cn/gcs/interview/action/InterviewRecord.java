package cn.gcs.interview.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.gcs.interview.service.InterviewService;
import cn.gcs.interview.vo.InterviewRecordItem;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class InterviewRecord extends ActionSupport {
	@Autowired
	private InterviewService interviewService;

	private int page;
	private int rows;

	private int total;
	private List<InterviewRecordItem> result;

	public String execute() throws Exception {

		result = interviewService.getInterviewRecord(page, rows);
		total = interviewService.getTotalNumberOfInterviewRecord();
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
