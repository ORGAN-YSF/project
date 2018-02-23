package com.ysf.test;

import org.springframework.util.Base64Utils;

import com.alibaba.fastjson.JSON;
import com.ysf.util.MD5Util;

public class JwtClientTest {
	public static void main(String[] args) {
		String compactJwt = "eyJhbGciOiJIUzUxMiJ9.eyJyYW5kb21LZXkiOiJrZmYyZnoiLCJzdWIiOiJhZG1pbiIsImV4cCI6MTUxOTE5OTY4MywiaWF0IjoxNTE4NTk0ODgzfQ.JOefcjEPGLl5EtOPu8ELAn80yd5SOJLjAL_VExUEZy4QT4K93O3OZoHL_dolFbga6h37u_VR7LkJ24Mj62EpFQ";
		String salt = "kff2fz";
		
		SimpleObject so = new SimpleObject();
		so.setAge(26);
		so.setName("孙文星");
		so.setTips("tips");
		
		String jsonStr = JSON.toJSONString(so);
		String encode = Base64Utils.encodeToString(jsonStr.getBytes());
		String md5 = MD5Util.encrypt(encode + salt);
		
		BaseTransferEntity entity = new BaseTransferEntity();
		entity.setObject(encode);
		entity.setSign(md5);
		
		System.out.println(JSON.toJSONString(entity));
	}
}
