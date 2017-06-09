package cn.gcs.core.action;

import cn.gcs.core.page.PageResult;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport{

	protected String ids;
	
	protected PageResult pageResult;
	private int page;
	private int rows;
	
	// 默认页号、页面大小
	public static int DEFAULT_PAGE_NO = 1;
	public static int DEFAULT_PAGE_SIZE = 10;
	
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}

	public PageResult getPageResult() {
		return pageResult;
	}

	public void setPageResult(PageResult pageResult) {
		this.pageResult = pageResult;
	}
	public int getPage() {
		if (page < 1) {
			page = DEFAULT_PAGE_NO;
		}
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		if (rows < 1) {
			rows = DEFAULT_PAGE_SIZE;
		}
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}

}
