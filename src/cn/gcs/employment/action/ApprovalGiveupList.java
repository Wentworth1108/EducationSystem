package cn.gcs.employment.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.gcs.employment.service.EmploymentService;
import cn.gcs.employment.vo.GiveupEmploymentRecordItem;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class ApprovalGiveupList extends ActionSupport {
	@Autowired
	private EmploymentService employmentService;

	private int page;
	private int rows;

	private int total;
	private List<GiveupEmploymentRecordItem> result;

	@Override
	public String execute() throws Exception {
		int userid = (int) ServletActionContext.getRequest().getSession()
				.getAttribute("id");
		result = employmentService.getGiveupEmploymentApply(page, rows, userid);
		total = employmentService.getTotalNumberOfGiveupApply(userid);
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

	public List<GiveupEmploymentRecordItem> getRows() {
		return result;
	}
}
