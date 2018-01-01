package com.ysf.util;

import com.ysf.common.util.SpringContextHolder;
import com.ysf.config.properties.YsfProperties;

public class ToolUtil {
	/**
	 * 获取是否开启验证码
	 */
	public static Boolean getKaptchaOpen() {
		return SpringContextHolder.getBean(YsfProperties.class).getKaptchaOpen();
	}
}
