package com.ysf.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ysf.bo.SaveUserReqBO;
import com.ysf.common.bo.RspInfoBO;
import com.ysf.common.constant.BaseRspConstants;
import com.ysf.dao.UserMapper;
import com.ysf.po.User;
import com.ysf.service.UserService;

/**
 * 用户服务实现类
 * @author sunwenxing
 */
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public RspInfoBO addUser(SaveUserReqBO saveUserReqBO) {
		User user = new User();
		BeanUtils.copyProperties(saveUserReqBO, user);
		userMapper.insert(user);
		
		RspInfoBO rsp = new RspInfoBO();
		rsp.setRespCode(BaseRspConstants.RSP_SUCCESS_CODE);
		rsp.setRespDesc(BaseRspConstants.RSP_SUCCESS_DESC);
		return rsp;
	}
}
