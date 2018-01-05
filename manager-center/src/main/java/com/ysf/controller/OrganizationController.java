package com.ysf.controller;

import java.util.Date;
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
import com.ysf.annotation.BusinessLog;
import com.ysf.bo.OrganizationReqBO;
import com.ysf.bo.OrganizationSaveReqBO;
import com.ysf.bo.RspOrganizationBO;
import com.ysf.common.bo.RspInfoBO;
import com.ysf.common.bo.RspPageBO;
import com.ysf.common.constant.dictmap.OrganizationDict;
import com.ysf.service.OrganizationService;
import com.ysf.util.ControllerUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
public class OrganizationController {
	@Autowired 
	private OrganizationService organizationService;
	
	/**
	 * 根据条件查询部门信息
	 */
	@ApiOperation(value="查询部门信息",notes="查询部门信息",tags={"tag"},response=JSONObject.class)
	@ApiImplicitParams(value={
		@ApiImplicitParam(name="orgName",value="组织机构名称",required=false,dataType="String",paramType="query"),
		@ApiImplicitParam(name="pageNo",value="当前页码",required=true,dataType="String",paramType="query"),
		@ApiImplicitParam(name="pageSize",value="每页显示数量",required=true,dataType="String",paramType="query")
	})
	@RequestMapping(value="/service/routing/qryOrganization",method = {RequestMethod.GET,RequestMethod.POST})
	public JSONObject qryOrganization(HttpServletRequest request) {
		
		String orgName = request.getParameter("orgName");
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");
		
		OrganizationReqBO orgReqBO = new OrganizationReqBO();
		orgReqBO.setOrgName(orgName);
		orgReqBO.setPageNo(Integer.valueOf(pageNo));
		orgReqBO.setPageSize(Integer.valueOf(pageSize));
		RspPageBO<RspOrganizationBO> rspPage = organizationService.selectOrganizationListPage(orgReqBO);
		
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

	/**
	 * 添加组织机构
	 */
	//@RequiresPermissions("/mgr/role_assign")  //利用shiro自带的权限检查
	@RequestMapping(value="/service/routing/addOrganization",method = {RequestMethod.GET,RequestMethod.POST})
	@BusinessLog(value="添加组织机构",key="autoCode",dict = OrganizationDict.class)
	public JSONObject addOrganization(HttpServletRequest request) {
		OrganizationSaveReqBO organizationSaveReqBO = getOrganizationSaveReqBO(request);
		
		RspInfoBO rsp = organizationService.insertOrganization(organizationSaveReqBO);
		return this.getData(rsp);
	}
	private OrganizationSaveReqBO getOrganizationSaveReqBO(HttpServletRequest request) {
		String autoCode = request.getParameter("autoCode");
		String parentId = request.getParameter("parentId");
		String title = request.getParameter("title");
		String alias = request.getParameter("alias");
		
		OrganizationSaveReqBO organizationSaveReqBO = new OrganizationSaveReqBO();
		organizationSaveReqBO.setAutoCode(autoCode);
		organizationSaveReqBO.setParentId(Long.valueOf(parentId));
		organizationSaveReqBO.setTitle(title);
		organizationSaveReqBO.setAlias(alias);
		organizationSaveReqBO.setCreateTime(new Date());
		return organizationSaveReqBO;
	}
	private JSONObject getData(RspInfoBO rsp) {
		JSONObject result = new JSONObject();
		result.put("respCode", rsp.getRespCode());
		result.put("respDesc", rsp.getRespDesc());
		return result;
	}
}
