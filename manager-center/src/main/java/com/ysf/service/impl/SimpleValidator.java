 package com.ysf.service.impl;

import org.springframework.stereotype.Service;

import com.ysf.service.IReqValidator;

@Service
public class SimpleValidator implements IReqValidator {
	private static final String USER_NAME = "admin";
	private static final String PASSWORD = "admin";
	@Override
	public boolean validate(String userName, String password) {
		if(USER_NAME.equals(userName) && PASSWORD.equals(password)) {
			return true;
		}
		return false;
	}

}
