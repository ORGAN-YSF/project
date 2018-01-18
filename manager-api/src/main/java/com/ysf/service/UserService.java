package com.ysf.service;

import com.ysf.bo.SaveUserReqBO;
import com.ysf.common.bo.RspInfoBO;

/**
 * 用户服务
 * @author sunwenxing
 */
public interface UserService {
	/**
	 * 添加用户
	 */
	RspInfoBO addUser(SaveUserReqBO saveUserReqBO);
}
