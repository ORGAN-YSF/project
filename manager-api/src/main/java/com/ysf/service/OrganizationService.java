package com.ysf.service;

import com.ysf.bo.OrganizationReqBO;
import com.ysf.bo.OrganizationSaveReqBO;
import com.ysf.bo.RspOrganizationBO;
import com.ysf.common.bo.RspInfoBO;
import com.ysf.common.bo.RspPageBO;

/**
 * 组织机构服务
 * @author sunwenxing
 */
public interface OrganizationService {
	/**
	 * 查询组织机构（分页）
	 */
	RspPageBO<RspOrganizationBO> selectOrganizationListPage(OrganizationReqBO orgReqBO);

	/**
	 * 新增组织机构
	 */
	RspInfoBO insertOrganization(OrganizationSaveReqBO organizationSaveReqBO);
}