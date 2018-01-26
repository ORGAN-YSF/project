package com.ysf.common.enums;

public enum ExceptionEnum {
	
	NO_PERMITION(405, "权限异常"),
	SERVER_ERROR(500, "服务器异常");
	
	private Integer code;
	private String message;
	
	ExceptionEnum(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}
	

	public void setCode(Integer code) {
		this.code = code;
	}
	

	public String getMessage() {
		return message;
	}
	

	public void setMessage(String message) {
		this.message = message;
	}
	
}