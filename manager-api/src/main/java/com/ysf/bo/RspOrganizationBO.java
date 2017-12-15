package com.ysf.bo;

import com.ysf.common.bo.RspInfoBO;

public class RspOrganizationBO extends RspInfoBO {
	private static final long serialVersionUID = 4395767891917544233L;
	
	private Long autoId;			//组织机构id
	
	private String title;			//组织机构名称

	public Long getAutoId() {
		return autoId;
	}
	

	public void setAutoId(Long autoId) {
		this.autoId = autoId;
	}
	

	public String getTitle() {
		return title;
	}
	

	public void setTitle(String title) {
		this.title = title;
	}
}
