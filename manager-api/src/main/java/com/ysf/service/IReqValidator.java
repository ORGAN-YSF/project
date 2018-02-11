package com.ysf.service;

public interface IReqValidator {
	//通过请求参数验证
	public boolean validate(String userName,String password);
}
