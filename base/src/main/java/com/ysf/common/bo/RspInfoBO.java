package com.ysf.common.bo;

import java.io.Serializable;

import com.ysf.common.constant.BaseRspConstants;

/**
 * 通用出参BO
 * @author sunwenxing
 */
public class RspInfoBO implements Serializable {
	private static final long serialVersionUID = 2433672866846959687L;
	/** 返回编码 */
	private String respCode = BaseRspConstants.RSP_SUCCESS_CODE;				
	/** 返回描述 */
	private String respDesc = BaseRspConstants.RSP_SUCCESS_DESC;

	public String getRespCode() {
		return respCode;
	}
	

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	

	public String getRespDesc() {
		return respDesc;
	}
	

	public void setRespDesc(String respDesc) {
		this.respDesc = respDesc;
	}
}
