package com.ysf.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ysf.common.bo.UserInfo;
import com.ysf.factory.LogTaskFactory;
import com.ysf.log.LogManager;
import com.ysf.util.HttpUtil;
import com.ysf.util.ShiroUtil;

@Controller
public class LoginController {
	
	public String REDIRECT = "redirect:";
	
	@RequestMapping(value = "/login",method = RequestMethod.GET) 
	public String login() {
        if (ShiroUtil.isAuthenticated() || ShiroUtil.getUserInfo() != null) {
        	return REDIRECT + "/index";
        } else {
            return "/login";
        }
	}
	
	@RequestMapping(value = "/login",method = RequestMethod.POST) 
	public String doLogin(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String rememberMe = request.getParameter("rememberMe");
		
		Subject currentUser = ShiroUtil.getSubject();
		
		UsernamePasswordToken token = new UsernamePasswordToken(username, password); 
		if("on".equals(rememberMe)) {
			token.setRememberMe(true);
		}
		UserInfo userInfo = ShiroUtil.getUserInfo();
		
		currentUser.login(token);
	
		LogManager.me().executeLog(LogTaskFactory.loginLog(userInfo.getUserId(),HttpUtil.getIp()));
		
		return null;
	}
}
