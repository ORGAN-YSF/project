package com.ysf.factory;

import javax.servlet.http.HttpServletRequest;
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
	/**
	 * 检查指定角色
	 */
	@Override
	public boolean check(Object[] permissions) {
		UserInfo userInfo = ShiroUtil.getUserInfo();
		if (null == userInfo) {
			return false;
		}
		String join = CollectionUtil.join(permissions, ",");
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
		String[] str = requestURI.split("/");
		if (str.length > 3) {
			requestURI = "/" + str[1] + "/" + str[2];
		}
		System.out.println(requestURI);
		if (ShiroUtil.hasPermission(requestURI)) {
			return true;
		}
		return false;
	}
}
