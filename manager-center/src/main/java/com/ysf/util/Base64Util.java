package com.ysf.util;

import org.springframework.util.Base64Utils;

public interface Base64Util {
	public static String encode(String beProtected) {
		return Base64Utils.encodeToString(beProtected.getBytes());
	}
	
	public static String decode(String securityCode) {
		byte[] decodeFromString = Base64Utils.decodeFromString(securityCode);
		return new String(decodeFromString);
	}
}
