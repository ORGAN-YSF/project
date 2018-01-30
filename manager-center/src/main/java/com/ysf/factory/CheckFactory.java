package com.ysf.factory;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import com.ysf.common.bo.UserInfo;
import com.ysf.common.util.CollectionUtil;
import com.ysf.listener.ConfigListener;
import com.ysf.util.HttpUtil;
import com.ysf.util.ShiroUtil;

@Component
@DependsOn("springContextHolder")
public class CheckFactory implements ICheckFactory {
	private static final Logger log = LoggerFactory.getLogger(CheckFactory.class);
	
	/**
	 * 检查指定角色
	 */
	@Override
	public boolean check(Object[] permissions) {
		UserInfo userInfo = ShiroUtil.getUserInfo();
		if (null == userInfo) {
			return false;
		}
		String join = CollectionUtil.join(permissions,",");
		if (ShiroUtil.hasAnyRoles(join)) {
			return true;
		}
		return false;
	}

	/**
	 * 检查全体角色
	 */
	@Override
	public boolean checkAll() {
		HttpServletRequest request = HttpUtil.getRequest();
		UserInfo user = ShiroUtil.getUserInfo();
		if (null == user) {
			return false;
		}
		String requestURI = request.getRequestURI().replaceFirst(ConfigListener.getConf().get("contextPath"),"");
		
		log.debug("检查全体角色请求路径:" + requestURI);
		
		String[] str = requestURI.split("/");
		
		log.debug(str.toString());
		
		if (str.length > 3) {
			requestURI = "/" + str[1] + "/" + str[2];
		}
	
		log.debug("检查全体角色请求路径:" + requestURI);
		
		if (ShiroUtil.hasPermission(requestURI)) {
			return true;
		}
		return false;
	}
}
