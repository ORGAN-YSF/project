package com.ysf.util;

import java.util.Random;

import com.ysf.common.util.SpringContextHolder;
import com.ysf.config.properties.YsfProperties;

public class ToolUtil {
	/**
	 * 获取是否开启验证码
	 */
	public static Boolean getKaptchaOpen() {
		return SpringContextHolder.getBean(YsfProperties.class).getKaptchaOpen();
	}
	
    /**
     * 获取随机位数的字符串
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
