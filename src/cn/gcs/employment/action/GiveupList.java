package cn.gcs.employment.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.gcs.employment.service.EmploymentService;
import cn.gcs.employment.vo.GiveupEmploymentRecordItem;

import com.opensymphony.xwork2.ActionSupport;

@Component
public class GiveupList extends ActionSupport {
	@Autowired
	private EmploymentService employmentService;

	private int page;
	private int rows;

	private int total;
	private List<GiveupEmploymentRecordItem> result;

	public String execute() throws Exception {

		result = employmentService.getGiveupRecord(page, rows);
		total = employmentService.getTotalNumberOfGiveupRecord();
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
