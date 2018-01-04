package com.ysf.common.constant;

public interface ConstantEnum {
	enum LogType {
		LOGIN("登录日志"),
	    LOGIN_FAIL("登录失败日志"),
	    EXIT("退出日志"),
	    EXCEPTION("异常日志"),
	    BUSSINESS("业务日志");

	    String message;

	    LogType(String message) {
	        this.message = message;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }
	}
	
	/**
	 * 管理员状态
	 * @author sunwenxing
	 */
	enum ManagerStatus {
	    OK(1, "启用"), FREEZED(2, "冻结"), DELETED(3, "被删除");
		
	    int code;
	    String message;

	    ManagerStatus(int code, String message) {
	        this.code = code;
	        this.message = message;
	    }

	    public int getCode() {
	        return code;
	    }

	    public void setCode(int code) {
	        this.code = code;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }

	    public static String valueOf(Integer value) {
	        if (value == null) {
	            return "";
	        } else {
	            for (ManagerStatus ms : ManagerStatus.values()) {
	                if (ms.getCode() == value) {
	                    return ms.getMessage();
	                }
	            }
	            return "";
	        }
	    }
	}
}
















