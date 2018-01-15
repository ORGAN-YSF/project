package com.ysf.factory;

import java.util.Date;
import java.util.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ysf.common.constant.BaseRspConstants;
import com.ysf.common.constant.ConstantEnum;
import com.ysf.common.util.SpringContextHolder;
import com.ysf.dao.LoginLogMapper;
import com.ysf.dao.OperationLogMapper;
import com.ysf.po.LoginLog;
import com.ysf.po.OperationLog;

public class LogTaskFactory {
	private static final Logger logger = LoggerFactory.getLogger(LogTaskFactory.class);
	private static OperationLogMapper operationLogMapper = SpringContextHolder.getBean(OperationLogMapper.class);
	private static LoginLogMapper loginLogMapper = SpringContextHolder.getBean(LoginLogMapper.class);
	
	public static TimerTask loginLog(final Long userId,final String ip) {
		return new TimerTask() {
			@Override
			public void run() {
				LoginLog loginLog = new LoginLog();
				loginLog.setCreateTime(new Date());
				loginLog.setIp(ip);
				loginLog.setLogname(ConstantEnum.LogType.LOGIN.getMessage());
				loginLog.setMessage(BaseRspConstants.RSP_SUCCESS_DESC);
				loginLog.setSucceed(BaseRspConstants.RSP_SUCCESS_DESC);
				loginLog.setUserid(userId);
				try {
					loginLogMapper.insert(loginLog);
				} catch (Exception e) {
					logger.error("创建登录日志异常!",e);
				}
			}
		};
	}
	
	public static TimerTask bussinessLog(final Long userId,final String businessName,
			final String className, final String methodName, final String msg) {
		return new TimerTask() {
			@Override
			public void run() {
				OperationLog operationLog = new OperationLog();
				operationLog.setLogtype(ConstantEnum.LogType.BUSSINESS.getMessage());
				operationLog.setLogname(businessName);
				operationLog.setUserId(userId);
				operationLog.setClassName(className);
				operationLog.setMethod(methodName);
				operationLog.setCreateTime(new Date());
				operationLog.setSucceed(BaseRspConstants.RSP_SUCCESS_DESC);
				operationLog.setMessage(msg);
				try {
					operationLogMapper.insert(operationLog);
				} catch (Exception e) {
					logger.error("创建业务日志异常!",e);
				}
			}
		};
	}
}
