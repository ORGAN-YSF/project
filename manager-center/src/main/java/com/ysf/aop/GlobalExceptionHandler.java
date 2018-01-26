package com.ysf.aop;

import java.lang.reflect.UndeclaredThrowableException;

import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.alibaba.fastjson.JSONObject;
import com.ysf.common.enums.ExceptionEnum;
import com.ysf.common.excecption.BusinessException;

@ControllerAdvice
public class GlobalExceptionHandler {
	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * 业务异常
	 */
	@ExceptionHandler(value=BusinessException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public JSONObject notFound(BusinessException e) {
		JSONObject result = new JSONObject();
		result.put("msgCode", e.getMsgCode());
		result.put("msgDesc", e.getMessage());
		return result;
	}

	/**
	 * 用户未登录
	 */
	@ExceptionHandler(value = AuthenticationException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public String notLogin(AuthenticationException e) {
		log.error("用户未登录:" + e);
		return "/login.jsp";
	}
	
	/**
	 * 无权访问该资源
     */
    @ExceptionHandler(UndeclaredThrowableException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public JSONObject credentials(UndeclaredThrowableException e) {
        log.error("权限异常!", e);
        JSONObject result = new JSONObject();
        result.put("msgCode", ExceptionEnum.NO_PERMITION.getCode());
        result.put("msgCode", ExceptionEnum.NO_PERMITION.getMessage());
        return result;
    }

    /**
     * 运行时异常
     */
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public JSONObject notFound(RuntimeException e) {
		log.error("运行时异常:", e);
		JSONObject result = new JSONObject();
		result.put("msgCode", ExceptionEnum.SERVER_ERROR.getCode());
		result.put("msgDesc", e.getMessage());
		return result;
	}
}
