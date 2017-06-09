package cn.gcs.core.page;

import java.util.ArrayList;
import java.util.List;

public class PageResult {

		// 总记录数
		private long totalCount;
		// 当前页号
		private int page;
		// 总页数
		private int totalPageCount;
		// 页大小
		private int rows;
		// 列表记录
		private List items;
		
		public PageResult() {
		}

		public PageResult(long totalCount, int page, int rows, List items) {
			this.items = items == null ? new ArrayList() : items;
			this.totalCount = totalCount;
			this.setPage(page);
			this.setRows(rows);
			if (totalCount != 0) {
				// 计算总页数
				int tem = (int) (totalCount / rows);
				this.totalPageCount = (totalCount % rows == 0) ? tem : (tem + 1);
			} else {
				this.setPage(0);
			}
		}
		
		public long getTotalCount() {
			return totalCount;
		}
		public void setTotalCount(long totalCount) {
			this.totalCount = totalCount;
		}
	
		public int getTotalPageCount() {
			return totalPageCount;
		}
		public void setTotalPageCount(int totalPageCount) {
			this.totalPageCount = totalPageCount;
		}
		
		public List getItems() {
			return items;
		}
		public void setItems(List items) {
			this.items = items;
		}

		public int getPage() {
			return page;
		}

		public void setPage(int page) {
			this.page = page;
		}

		public int getRows() {
			return rows;
		}
		public void setRows(int rows) {
			this.rows = rows;
		}
}
