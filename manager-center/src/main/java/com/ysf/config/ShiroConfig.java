package com.ysf.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.ServletContainerSessionManager;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ysf.config.properties.YsfProperties;
import com.ysf.realm.ShiroDbRealm;

/**
 * shiro配置
 * @author sunwenxing
 */
@Configuration
public class ShiroConfig {
	/**
	 * shiro管理器
	 */
	@Bean
	public DefaultWebSecurityManager securityManager(CookieRememberMeManager rememberMeManager,CacheManager cacheManager,SessionManager sessionManager) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		
		securityManager.setRealm(this.shiroDbRealm());
		securityManager.setCacheManager(cacheManager);
		securityManager.setSessionManager(sessionManager);
		securityManager.setRememberMeManager(rememberMeManager);
		
		return securityManager;
	}
	
	/**
	 * 自定义realm
	 */
	@Bean
	public ShiroDbRealm shiroDbRealm() {
        return new ShiroDbRealm();
    }

	/**
	 * rememberMe管理器
	 */
	@Bean
	public CookieRememberMeManager rememberMeManager(SimpleCookie simpleCookie) {
		CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
		rememberMeManager.setCipherKey(Base64.decode("eXNmAAAAAAAAAAAAAAAAAA=="));
		rememberMeManager.setCookie(simpleCookie);
		return rememberMeManager;
	}
	
	/**
	 * rememberMe的Cookie
	 */
	@Bean
	public SimpleCookie rememberMeCookie() {
		SimpleCookie cookie = new SimpleCookie("rememberMe");
		cookie.setHttpOnly(true);//防止xss攻击
		cookie.setMaxAge(24 * 60 * 60);
		return cookie;
	}
	
	/**
	 * 缓存管理器
	 */
	@Bean
	public CacheManager cacheManager(EhCacheManagerFactoryBean ehcache) {
		EhCacheManager cacheManager = new EhCacheManager();
		cacheManager.setCacheManager(ehcache.getObject());
		return cacheManager;
	}
	
	/**
	 * spring session管理器（多机环境）
	 */
	@Bean
	@ConditionalOnProperty(prefix = "ysf", name = "spring-session-open", havingValue = "true")
	public ServletContainerSessionManager servletContainerSessionManager() {
		return new ServletContainerSessionManager();
	} 
	
	/**
	 * spring session管理器（单机环境）
	 */
    @Bean
    @ConditionalOnProperty(prefix = "ysf", name = "spring-session-open", havingValue = "false")
    public DefaultWebSessionManager defaultWebSessionManager(CacheManager cacheShiroManager,YsfProperties ysfProperties) {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setCacheManager(cacheShiroManager);
        sessionManager.setSessionValidationInterval(ysfProperties.getSessionValidationInterval() * 1000);
        sessionManager.setGlobalSessionTimeout(ysfProperties.getSessionInvalidateTime() * 1000);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        Cookie cookie = new SimpleCookie(ShiroHttpSession.DEFAULT_SESSION_ID_NAME);
        cookie.setName("shiroCookie");
        cookie.setHttpOnly(true);
        sessionManager.setSessionIdCookie(cookie);
        return sessionManager;
    }
    
    /**
     * shiro过滤器链
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(DefaultWebSecurityManager securityManager) {
    	ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
    	shiroFilter.setSecurityManager(securityManager);
    	//登录url
    	shiroFilter.setLoginUrl("/login");
    	//登录成功后跳转的url
    	shiroFilter.setSuccessUrl("/index");
    	//无权限跳转页面
    	shiroFilter.setUnauthorizedUrl("/error");
    	
    	/**
    	 * 配置拦截器链
    	 * 顺序从上到下依次降低
    	 */
        Map<String, String> hashMap = new LinkedHashMap<>();
        hashMap.put("/static/**", "anon");
        hashMap.put("/login", "anon");
        hashMap.put("/global/sessionError","anon");
        hashMap.put("/kaptcha","anon");
        hashMap.put("/**","user");
        shiroFilter.setFilterChainDefinitionMap(hashMap);
    	return shiroFilter;
    }
    
    /**
     * 在方法中注入参数，进行代理控制
     */
    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean(DefaultWebSecurityManager securityManager) {
    	MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
    	methodInvokingFactoryBean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
    	methodInvokingFactoryBean.setArguments(new Object[]{securityManager});
    	return methodInvokingFactoryBean;
    }
    
    /**
     * Shiro生命周期处理器:
     * 用于在实现了Initializable接口的Shiro bean初始化时调用Initializable接口回调(例如:UserRealm)
     * 在实现了Destroyable接口的Shiro bean销毁时调用 Destroyable接口回调(例如:DefaultSecurityManager)
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
    	return new LifecycleBeanPostProcessor();
    }
    
    /**
     * 启用shiro授权注解拦截方式,AOP式方法级权限检查
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
    	AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
    	advisor.setSecurityManager(securityManager);
    	return advisor;
    } 
}
