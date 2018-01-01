package com.ysf.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix=YsfProperties.YSF_PREFIX)
public class YsfProperties {
	public static final String YSF_PREFIX = "ysf";

	private Boolean swaggerOpen;					//是否开启swagger
	
	private Boolean kaptchaOpen;					//是否开启登录时验证码
	  
	private Boolean sessionOpen;					//是否开启session超时验证
	
	private Boolean mutiDatasourceOpen;				//是否开启多数据源
	  
	private Boolean springSessionOpen;				//是否开启spring session，多机环境需要开启
	  
	private Long sessionInvalidateTime;				//session失效时间
	 
	private Long sessionValidationInterval;			//多久检测一次失效的session

	public Boolean getSwaggerOpen() {
		return swaggerOpen;
	}
	

	public void setSwaggerOpen(Boolean swaggerOpen) {
		this.swaggerOpen = swaggerOpen;
	}
	

	public Boolean getKaptchaOpen() {
		return kaptchaOpen;
	}
	

	public void setKaptchaOpen(Boolean kaptchaOpen) {
		this.kaptchaOpen = kaptchaOpen;
	}
	

	public Boolean getSessionOpen() {
		return sessionOpen;
	}
	

	public void setSessionOpen(Boolean sessionOpen) {
		this.sessionOpen = sessionOpen;
	}
	

	public Boolean getMutiDatasourceOpen() {
		return mutiDatasourceOpen;
	}
	

	public void setMutiDatasourceOpen(Boolean mutiDatasourceOpen) {
		this.mutiDatasourceOpen = mutiDatasourceOpen;
	}
	

	public Boolean getSpringSessionOpen() {
		return springSessionOpen;
	}
	

	public void setSpringSessionOpen(Boolean springSessionOpen) {
		this.springSessionOpen = springSessionOpen;
	}
	

	public Long getSessionInvalidateTime() {
		return sessionInvalidateTime;
	}
	

	public void setSessionInvalidateTime(Long sessionInvalidateTime) {
		this.sessionInvalidateTime = sessionInvalidateTime;
	}
	

	public Long getSessionValidationInterval() {
		return sessionValidationInterval;
	}
	

	public void setSessionValidationInterval(Long sessionValidationInterval) {
		this.sessionValidationInterval = sessionValidationInterval;
	}
}
