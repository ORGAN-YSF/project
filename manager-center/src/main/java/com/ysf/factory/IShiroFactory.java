package com.ysf.factory;

import java.util.List;
import org.apache.shiro.authc.SimpleAuthenticationInfo;

import com.ysf.common.bo.UserInfo;
import com.ysf.po.User;

public interface IShiroFactory {
    /**
     * 根据用户名获取用户信息
     */
    User getUserByUserName(String userName);

    /**
     * 根据系统用户获取用户更多信息
     */
    UserInfo shiroUser(User user);

    /**
     * 获取权限列表通过角色id
     */
    List<String> findPermissionsByRoleId(Long roleId);

    /**
     * 根据角色id获取角色名称
     */
    String findRoleNameByRoleId(Long roleId);

    /**
     * 获取shiro的认证信息
     */
    SimpleAuthenticationInfo info(UserInfo userInfo,User user,String realmName);
}
