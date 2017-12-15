package com.ysf.service;

import com.ysf.bo.OrganizationReqBO;
import com.ysf.bo.RspOrganizationBO;
import com.ysf.common.bo.RspPageBO;

/**
 * 组织机构服务
 * @author sunwenxing
 */
public interface OrganizationService {
	RspPageBO<RspOrganizationBO> selectOrganizationListPage(OrganizationReqBO orgReqBO);
}