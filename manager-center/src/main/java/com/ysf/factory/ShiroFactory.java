package com.ysf.factory;

import java.util.List;

import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ysf.common.bo.UserInfo;
import com.ysf.common.constant.ConstantEnum.ManagerStatus;
import com.ysf.dao.UserMapper;
import com.ysf.po.User;

@Service
@DependsOn("springContextHolder")
@Transactional(readOnly=true)
public class ShiroFactory implements IShiroFactory {
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User getUserByUserName(String userName) {
		User user = userMapper.selectByUserName(userName);
        // 账号不存在
        if (null == user) {
            throw new CredentialsException();
        }
        // 账号被冻结
        if (user.getStatus() != ManagerStatus.OK.getCode()) {
            throw new LockedAccountException();
        }
        return user;
	}

	@Override
	public UserInfo shiroUser(User user) {
		return null;
	}

	@Override
	public List<String> findPermissionsByRoleId(Long roleId) {
		return null;
	}

	@Override
	public String findRoleNameByRoleId(Long roleId) {
		return null;
	}

	@Override
	public SimpleAuthenticationInfo info(UserInfo userInfo, User user, String realmName) {
		return null;
	}
}
