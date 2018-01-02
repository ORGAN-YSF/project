package com.ysf.factory;

import java.util.Date;
import java.util.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ysf.common.constant.BaseRspConstants;
import com.ysf.common.constant.ConstantEnum;
import com.ysf.common.util.SpringContextHolder;
import com.ysf.dao.OperationLogMapper;
import com.ysf.po.OperationLog;

public class LogTaskFactory {
	private static final Logger logger = LoggerFactory.getLogger(LogTaskFactory.class);
	private static OperationLogMapper operationLogMapper = SpringContextHolder.getBean(OperationLogMapper.class);

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
