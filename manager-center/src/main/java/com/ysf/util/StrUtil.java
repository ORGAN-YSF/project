package com.ysf.util;

/**
 * 字符串工具类
 * @author sunwenxing
 */
public class StrUtil {
	public static String removeSuffix(String str, String suffix) {
		if(isEmpty(str) || isEmpty(suffix)){
			return str;
		}
		if (str.endsWith(suffix)) {
			return str.substring(0, str.length() - suffix.length());
		}
		return str;
	}
	
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}
}
