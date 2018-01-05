package com.ysf.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.ysf.common.bo.UserInfo;
import com.ysf.factory.IShiroFactory;
import com.ysf.factory.ShiroFactory;
import com.ysf.po.User;

public class ShiroDbRealm extends AuthorizingRealm {
	/**
	 * 登录认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
		IShiroFactory shiroFactory = ShiroFactory.me();
		User user = shiroFactory.getUserByUserName(usernamePasswordToken.getUsername());
		UserInfo userInfo = shiroFactory.shiroUser(user);
		SimpleAuthenticationInfo info = shiroFactory.info(userInfo, user, super.getName());
		return info;
	}

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		IShiroFactory shiroFactory = ShiroFactory.me();
		UserInfo userInfo = (UserInfo) principals.getPrimaryPrincipal();
		Set<String> permissionSet = new HashSet<>();
		Set<String> roleNameSet = new HashSet<>();
		
		List<Long> roleList = userInfo.getRoleList();
		for (Long roleId : roleList) {
			List<String> permissions = shiroFactory.findPermissionsByRoleId(roleId);
			if (permissions != null) {
				for (String permission : permissions) {
					if (StringUtils.isNotBlank(permission)) {
						permissionSet.add(permission);
					}
				}
			}
			String roleName = shiroFactory.findRoleNameByRoleId(roleId);
			roleNameSet.add(roleName);
		}
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(permissionSet);
		info.addRoles(roleNameSet);
		return info;
	}

	/**
	 * 设置加密方式
	 */
	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		HashedCredentialsMatcher md5 = new HashedCredentialsMatcher();
		md5.setHashAlgorithmName("MD5");
		md5.setHashIterations(1024);
		super.setCredentialsMatcher(md5);
	}
}

