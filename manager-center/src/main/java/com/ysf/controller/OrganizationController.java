package com.ysf.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ysf.bo.OrganizationReqBO;
import com.ysf.bo.RspOrganizationBO;
import com.ysf.common.bo.RspPageBO;
import com.ysf.service.OrganizationService;
import com.ysf.util.ControllerUtil;

@RestController
public class OrganizationController {
	@Autowired 
	private OrganizationService organizationService;
	
	@RequestMapping(value="/service/routing/qryOrganization",method = {RequestMethod.GET,RequestMethod.POST})
	public JSONObject qryOrganization(HttpServletRequest request) {
		String orgName = request.getParameter("orgName");
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");
		
		OrganizationReqBO orgReqBO = new OrganizationReqBO();
		orgReqBO.setOrgName(orgName);
		orgReqBO.setPageNo(Integer.valueOf(pageNo));
		orgReqBO.setPageSize(Integer.valueOf(pageSize));
		RspPageBO<RspOrganizationBO> rspPage = 
				organizationService.selectOrganizationListPage(orgReqBO);
		
		return this.getData(rspPage);
	}

	private JSONObject getData(RspPageBO<RspOrganizationBO> rsp) {
		JSONObject jsonObject = new JSONObject();
		
		jsonObject.put("respCode", rsp.getRespCode());
		jsonObject.put("respDesc", rsp.getRespDesc());
		JSONObject dataJson = new JSONObject();
		dataJson.put("totalPage",JSON.toJSON(rsp.getTotal()));
		dataJson.put("pageNo",JSON.toJSON(rsp.getPageNo()));
		dataJson.put("recordsTotal",JSON.toJSON(rsp.getRecordsTotal()));
		List<RspOrganizationBO> list = rsp.getRows();
		JSONArray array = new JSONArray();
		if (null != list && list.size() > 0) {
			array = JSON.parseArray(JSON.toJSONString(list,ControllerUtil.valueFilter,SerializerFeature.WriteMapNullValue));
		}
		dataJson.put("rows", array);
		jsonObject.put("data",dataJson);
		
		return jsonObject;
	}
}
