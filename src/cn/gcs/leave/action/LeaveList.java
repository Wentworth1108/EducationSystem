package cn.gcs.leave.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.gcs.leave.service.LeaveService;
import cn.gcs.leave.vo.LeaveRecordItem;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class LeaveList extends ActionSupport {
	@Autowired
	private LeaveService leaveService;

	private int page;
	private int rows;

	private int total;
	private List<LeaveRecordItem> result;

	public String execute() throws Exception {

		result = leaveService.getLeaveRecord(page, rows);
		total = leaveService.getTotalNumberOfLeaveRecord();
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

	public List<LeaveRecordItem> getRows() {
		return result;
	}
}
