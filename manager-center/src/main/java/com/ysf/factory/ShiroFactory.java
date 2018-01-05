package com.ysf.factory;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.ysf.common.bo.UserInfo;
import com.ysf.common.constant.ConstantEnum.ManagerStatus;
import com.ysf.common.util.SpringContextHolder;
import com.ysf.dao.DeptMapper;
import com.ysf.dao.MenuMapper;
import com.ysf.dao.RoleMapper;
import com.ysf.dao.UserMapper;
import com.ysf.po.Dept;
import com.ysf.po.Role;
import com.ysf.po.User;

@Component
@DependsOn("springContextHolder")
@Transactional(readOnly=true)
public class ShiroFactory implements IShiroFactory {
	
	public static IConstantFactory me() {
        return SpringContextHolder.getBean("shiroFactory");
    }
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private DeptMapper deptMapper;
	@Autowired 
	private MenuMapper menuMapper;
	@Autowired
	private RoleMapper roleMapper;
	
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
		UserInfo userInfo = new UserInfo();
		userInfo.setUserId(user.getUserId());
		userInfo.setAccount(user.getAccount());
		userInfo.setUserName(user.getName());
		userInfo.setDeptId(user.getDeptId());
		
		Dept dept = deptMapper.selectByPrimaryKey(user.getDeptId());
		if(dept != null) {
			userInfo.setDeptName(dept.getFullName());
		}
		
		String roleId = user.getRoleId();
		//将roleId做转化(roleId是以,为间隔的字符串)
		if(StringUtils.isNotBlank(roleId)) {
			String[] roleIdArray = roleId.split(",");
			List<Long> roleIds = new ArrayList<>();
			for (String role : roleIdArray) {
				Long value = Long.valueOf(role);
				roleIds.add(value);
			}
			List<String> roleNames = roleMapper.selectByRoleIds(roleIds);
			userInfo.setRoleList(roleIds);
			userInfo.setRoleNames(roleNames);
		}
		return userInfo;
	}

	@Override
	public List<String> findPermissionsByRoleId(Long roleId) {
		List<String> urls = menuMapper.selectUrlsByRoleId(roleId);
		return urls;
	}

	@Override
	public String findRoleNameByRoleId(Long roleId) {
		Role role = roleMapper.selectByPrimaryKey(roleId);
		if(role != null) {
			return role.getName();
		}
		return null;
	}

	@Override
	public SimpleAuthenticationInfo info(UserInfo userInfo, User user, String realmName) {
		String password = user.getPassword();
		//密码盐
		String salt = user.getSalt();
		ByteSource passwordSalt = new Md5Hash(salt);
		return new SimpleAuthenticationInfo(userInfo, password, passwordSalt, realmName);
	}
}
