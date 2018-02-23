package com.ysf.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

@RestController
public class HelloController {
	@RequestMapping("/hello")
	public JSONObject hello() {
		JSONObject jo = new JSONObject();
		jo.put("respCode",200);
		jo.put("respDesc", "请求成功");
		return jo;
	}
}
