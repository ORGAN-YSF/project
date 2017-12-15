package com.ysf.bo;

import com.ysf.common.bo.ReqPageBO;

public class OrganizationReqBO extends ReqPageBO {
	private static final long serialVersionUID = 2741135449608488441L;

	private String orgName;					//组织机构名称

	public String getOrgName() {
		return orgName;
	}
	

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}
