package com.ysf.service.impl;

import java.util.LinkedList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ysf.bo.OrganizationReqBO;
import com.ysf.bo.OrganizationSaveReqBO;
import com.ysf.bo.RspOrganizationBO;
import com.ysf.common.bo.RspInfoBO;
import com.ysf.common.bo.RspPageBO;
import com.ysf.common.constant.BaseRspConstants;
import com.ysf.common.page.Page;
import com.ysf.dao.OrgOrganizationMapper;
import com.ysf.po.OrgOrganization;
import com.ysf.service.OrganizationService;

@Service
public class OrganizationServiceImpl implements OrganizationService {
	
	@Autowired 
	private OrgOrganizationMapper orgOrganizationMapper;
	
	/**
	 * 分页查询组织机构
	 */
	@Override
	public RspPageBO<RspOrganizationBO> selectOrganizationListPage(OrganizationReqBO orgReqBO) {
		Page<OrgOrganization> page = new Page<>(orgReqBO.getPageNo(), orgReqBO.getPageSize());
		
		List<OrgOrganization> list = orgOrganizationMapper.selectOrgListPage(orgReqBO.getOrgName(),page);
		
		RspPageBO<RspOrganizationBO> resultPage = new RspPageBO<>();
		
		if(CollectionUtils.isEmpty(list)) {
			resultPage.setRespCode(BaseRspConstants.RSP_SUCCESS_CODE);
			resultPage.setRespDesc("无组织机构记录");
			return resultPage;
		}
		
		List<RspOrganizationBO> rows = new LinkedList<>();
		for (OrgOrganization org : list) {
			RspOrganizationBO rsp = new RspOrganizationBO();
			BeanUtils.copyProperties(org, rsp);
			rows.add(rsp);
		}
		
		resultPage.setRecordsTotal(page.getTotalRecord());
		resultPage.setTotal(page.getTotalPage());
		resultPage.setRespCode(BaseRspConstants.RSP_SUCCESS_CODE);
		resultPage.setRespDesc("查询分页组织机构成功！");
		resultPage.setRows(rows);
		return resultPage;
	}
	
	/**
	 * 添加组织机构
	 */
	@Override
	public RspInfoBO insertOrganization(OrganizationSaveReqBO organizationSaveReqBO) {
		OrgOrganization record = new OrgOrganization();
		BeanUtils.copyProperties(organizationSaveReqBO, record);
		orgOrganizationMapper.insert(record);
		RspInfoBO rsp = new RspInfoBO();
		return rsp;
	}
}
