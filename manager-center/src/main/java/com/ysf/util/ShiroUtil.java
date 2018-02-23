package com.ysf.util;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;

import com.ysf.common.bo.UserInfo;
import com.ysf.common.constant.Constant;
import com.ysf.factory.ConstantFactory;

public class ShiroUtil {
	private static final String NAMES_DELIMETER = ",";

	//加盐参数
	public final static String hashAlgorithmName = "MD5";

	//循环次数
	public final static int hashIterations = 1024;

	//加密
	public static String md5(String password,String salt) {
		ByteSource saltSource = new Md5Hash(salt);
		return new SimpleHash(hashAlgorithmName, password, saltSource, hashIterations).toString();
	}

	//获取随机盐值
	public static String getRandomSalt(int length) {
		return ToolUtil.getRandomString(length);
	}

	//获取当前subject
	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	//获取完整用户
	public static UserInfo getUserInfo() {
		boolean userFlag = isUser();
		if(userFlag) {
			return (UserInfo) getSubject().getPrincipals().getPrimaryPrincipal();
		}
		return null;
	}
	
	public static boolean isUser() {
		return getSubject() != null && getSubject().getPrincipal() != null;
	}

	//从shiro获取session
	public static Session getSession() {
		return getSubject().getSession();
	}

	//从shiro中获取指定sessionKey
	@SuppressWarnings("unchecked")
	public static <T> T getSessionAttr(String key) {
		Session session = getSession();
		return session != null ? (T) session.getAttribute(key) : null;
	}

	//设置shiro的sessionKey
	public static void setSessionAttr(String key, Object value) {
		Session session = getSession();
		session.setAttribute(key, value);
	}

	//移除shiro指定的sessionKey
	public static void removeSessionAttr(String key) {
		Session session = getSession();
		if (session != null)
			session.removeAttribute(key);
	}

	//判断是否具有该角色,true为有
	public static boolean hasRole(String roleName) {
		return getSubject()!=null&&StringUtils.isNotBlank(roleName)&&getSubject().hasRole(roleName);
	}

	//判断是否无该角色，true为无
	public static boolean lacksRole(String roleName) {
		return !hasRole(roleName);
	}

	//判断是否有传入的任一角色，有的话返回true
	public static boolean hasAnyRoles(String roleNames) {
		boolean hasAnyRole = false;
		Subject subject = getSubject();
		if (subject != null && roleNames != null && roleNames.length() > 0) {
			for (String role : roleNames.split(NAMES_DELIMETER)) {
				if (subject.hasRole(role.trim())) {
					hasAnyRole = true;
					break;
				}
			}
		}
		return hasAnyRole;
	}

	//判断是否有传入的全部角色，有的话返回true
	public static boolean hasAllRoles(String roleNames) {
		boolean hasAllRole = true;
		Subject subject = getSubject();
		if (subject != null && roleNames != null && roleNames.length() > 0) {
			for (String role : roleNames.split(NAMES_DELIMETER)) {
				if (!subject.hasRole(role.trim())) {
					hasAllRole = false;
					break;
				}
			}
		}
		return hasAllRole;
	}

	//判断当前用户是否有传入的权限，返回true为有
	public static boolean hasPermission(String permission) {
		return getSubject() != null && permission != null
				&& permission.length() > 0
				&& getSubject().isPermitted(permission);
	}

	//判断当前用户是否有无传入的权限，返回true为无
	public static boolean lacksPermission(String permission) {
		return !hasPermission(permission);
	}
	
    /**
     * 已认证通过的用户。不包含已记住的用户，这是与user标签的区别所在。与notAuthenticated搭配使用
     * @return 通过身份验证：true，否则false
     */
    public static boolean isAuthenticated() {
        return getSubject() != null && getSubject().isAuthenticated();
    }
    
    /**
     * 未认证通过用户，与authenticated标签相对应
     * @return 没有通过身份验证：true，否则false
     */
    public static boolean notAuthenticated() {
        return !isAuthenticated();
    }
    
    /**
     * 输出当前用户信息，通常为登录帐号信息。
     */
    public static String principal() {
        if (getSubject() != null) {
            Object principal = getSubject().getPrincipal();
            return principal.toString();
        }
        return "";
    }

    /**
     * 获取当前用户的部门数据范围的集合
     */
    public static List<Long> getDeptDataScope() {
        Long deptId = getUserInfo().getDeptId();
        List<Long> subDeptIds = ConstantFactory.me().getSubDeptId(deptId);
        subDeptIds.add(deptId);
        return subDeptIds;
    }

    /**
     * 判断当前用户是否是超级管理员
     */
    public static boolean isAdmin() {
        List<Long> roleList = ShiroUtil.getUserInfo().getRoleList();
        for (Long role : roleList) {
            String singleRoleTip = ConstantFactory.me().getSingleRoleTip(role);
            if (Constant.ADMIN_NAME.equals(singleRoleTip)) {
                return true;
            }
        }
        return false;
    }
}
