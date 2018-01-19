package com.ysf.controller;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.ysf.annotation.Permission;
import com.ysf.bo.SaveUserReqBO;
import com.ysf.common.bo.RspInfoBO;
import com.ysf.common.constant.ConstantEnum;
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
	
	/**
	 * 新增用户
	 */
	@Permission
	@RequestMapping(value="/addUser",method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addUser(SaveUserReqBO saveUserReqBO) {
		String salt = ShiroUtil.getRandomSalt(5);
		String password = saveUserReqBO.getPassword() + salt;
		saveUserReqBO.setPassword(password);
		saveUserReqBO.setSalt(salt);
		saveUserReqBO.setCreateTime(new Date());
		saveUserReqBO.setStatus(ConstantEnum.ManagerStatus.OK.getCode());
		
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
}