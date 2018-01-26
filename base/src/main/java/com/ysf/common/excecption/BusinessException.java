package com.ysf.common.excecption;

public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1882392027560143655L;
	
	private String msgCode;						//异常编码
	
	private String msgDesc;						//异常描述

	public String getMsgCode() {
		return msgCode;
	}
	
	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}
	
	public String getMsgDesc() {
		return msgDesc;
	}
	
	public void setMsgDesc(String msgDesc) {
		this.msgDesc = msgDesc;
	}

	public BusinessException(String msgCode, String msgDesc) {
		super(msgDesc);
		this.msgCode = msgCode;
		this.msgDesc = msgDesc;
	}
}
