package com.ysf.common.page;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class Page<T> extends Pagination implements PageBo<T>,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5040028311467949908L;
	private List<T> records = Collections.emptyList();

	protected Page() {
	}

	public Page(int current) {
		super(current);
	}

	public Page(int current, int size) {
		super(current, size);
	}
	
	public Page(PageBo<T> pageBo){
	    super(pageBo.getCurrentPage(), pageBo.getPageSize());
	    setTotalRecord(pageBo.getTotalRecord());
	    setRecords(pageBo.getRecords());
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	@Override
	public String toString() {
		StringBuffer pg = new StringBuffer();
		pg.append(" Page:{ [").append(super.toString()).append("], ");
		if (records != null) {
			pg.append("records:").append(records.size());
		} else {
			pg.append("records is null");
		}
		return pg.append(" }").toString();
	}
}
