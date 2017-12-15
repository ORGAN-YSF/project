package com.ysf.common.page;

import org.apache.ibatis.session.RowBounds;

public class Pagination extends RowBounds {
	public static final int DEFAULT_PAGE_SIZE = 10;

	/* 总记录数 */
	private int totalRecord;

	/* 每页显示条数 */
	private int pageSize = DEFAULT_PAGE_SIZE;

	/* 总页数 */
	private int totalPage;

	/* 当前页 */
	private int currentPage = 1;

	public Pagination() {
		super();
	}

	public Pagination(int currentPage) {
		super(offsetCurrent(currentPage) * DEFAULT_PAGE_SIZE, DEFAULT_PAGE_SIZE);
		if (currentPage > 1) {
			this.currentPage = currentPage;
		}
	}

	public Pagination(int currentPage, int pageSize) {
		super(offsetCurrent(currentPage) * pageSize, pageSize);
		if (currentPage > 1) {
			this.currentPage = currentPage;
		}
		this.pageSize = pageSize;
	}

	protected static int offsetCurrent(int currentPage) {
		if (currentPage > 0) {
			return currentPage - 1;
		}
		return 0;
	}

	public boolean hasPrevious() {
		return this.currentPage > 1;
	}

	public boolean hasNext() {
		return this.currentPage < this.totalPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		this.totalPage = this.totalRecord / this.pageSize;
		if (this.totalRecord % this.pageSize != 0) {
			this.totalPage++;
		}
		/*if (this.currentPage > this.totalPage) {
			this.currentPage = 1;
		}*/
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
	
	 public void setPageSize(int pageSize) {
	        this.pageSize = pageSize;
	 }
	
	public int getCurrentOffset() {
		return (this.currentPage - 1)*this.pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	@Override
	public String toString() {
		return "Pagination { totalRecord=" + totalRecord + " ,pageSize="
				+ pageSize + " ,totalPage=" + totalPage + " ,currentPage="
				+ currentPage + " }";
	}
}