package com.ysf.bo;

import java.io.Serializable;

public class AuthResponse implements Serializable {
	private static final long serialVersionUID = -6986058763298928108L;

	private String token;
	
	private String randomKey;
	
	public AuthResponse() {
		super();
	}

	public AuthResponse(String token, String randomKey) {
		this.token = token;
		this.randomKey = randomKey;
	}

	public String getToken() {
		return token;
	}
	

	public void setToken(String token) {
		this.token = token;
	}
	

	public String getRandomKey() {
		return randomKey;
	}
	

	public void setRandomKey(String randomKey) {
		this.randomKey = randomKey;
	}
}
