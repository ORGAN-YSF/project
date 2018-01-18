package com.ysf.controller;

import java.text.ParseException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.ysf.bo.SaveUserReqBO;
import com.ysf.common.bo.RspInfoBO;
import com.ysf.common.constant.ConstantEnum.ManagerStatus;
import com.ysf.common.util.DateUtil;
import com.ysf.service.UserService;
import com.ysf.util.ShiroUtil;

/**
 * 用户控制器
 * @author sunwenxing
 */
@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/addUser",method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addUser(HttpServletRequest request) {
		SaveUserReqBO saveUserReqBO = getRequestData(request);
		
		RspInfoBO rsp = userService.addUser(saveUserReqBO);
		
		JSONObject result = dealResponse(rsp);
		return result;
	}
	
	private JSONObject dealResponse(RspInfoBO rsp) {
		JSONObject result = new JSONObject();
		
		result.put("respCode", rsp.getRespCode());
		result.put("respDesc", rsp.getRespDesc());
		
		return result;
	}

	private SaveUserReqBO getRequestData(HttpServletRequest request) {
		SaveUserReqBO saveUserReqBO = new SaveUserReqBO();
		String avatar = request.getParameter("avatar");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String birthday = request.getParameter("birthday");
		String sex = request.getParameter("sex");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String roleId = request.getParameter("roleId");
		String deptId = request.getParameter("deptId");
		
		saveUserReqBO.setAccount(account);
		saveUserReqBO.setAvatar(avatar);
		String salt = ShiroUtil.getRandomSalt(5);
		saveUserReqBO.setPassword(password + salt);
		try {
			saveUserReqBO.setBirthday(DateUtil.StringLongToDate(birthday));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		saveUserReqBO.setCreateTime(new Date());
		if(StringUtils.isNotBlank(deptId)) {
			saveUserReqBO.setDeptId(Long.valueOf(deptId));
		}
		saveUserReqBO.setEmail(email);
		saveUserReqBO.setName(name);
		saveUserReqBO.setPhone(phone);
		saveUserReqBO.setSalt(salt);
		if(StringUtils.isNotBlank(sex)) {
			saveUserReqBO.setSex(Integer.valueOf(sex));
		}
		saveUserReqBO.setRoleId(roleId);
		saveUserReqBO.setStatus(ManagerStatus.OK.getCode());
		saveUserReqBO.setVersion(1);
		return saveUserReqBO;
	}
}
