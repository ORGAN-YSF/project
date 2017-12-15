package com.ysf.common.bo;

import java.io.Serializable;

public class ReqPageBO implements Serializable {
	private static final long serialVersionUID = -4650414195122012695L;
	/** 第几页 */
	private int               pageNo           = 1;
	/** 每页的数量 */
	private int               pageSize         = 10;
	
	public int getPageNo() {
		return pageNo;
	}
	
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
