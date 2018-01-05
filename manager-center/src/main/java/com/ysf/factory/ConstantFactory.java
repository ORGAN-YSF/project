package com.ysf.factory;

import java.util.List;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.ysf.common.util.SpringContextHolder;
import com.ysf.dao.DeptMapper;
import com.ysf.dao.OrgOrganizationMapper;
import com.ysf.dao.RoleMapper;
import com.ysf.dao.UserMapper;
import com.ysf.po.OrgOrganization;
import com.ysf.po.Role;
import com.ysf.po.User;

@Component
@DependsOn("springContextHolder")
public class ConstantFactory implements IConstantFactory {
	public static IConstantFactory me() {
        return SpringContextHolder.getBean("constantFactory");
    }
	
	private OrgOrganizationMapper orgOrganizationMapper = SpringContextHolder.getBean(OrgOrganizationMapper.class);
	private UserMapper userMapper = SpringContextHolder.getBean(UserMapper.class);
	private DeptMapper deptMapper = SpringContextHolder.getBean(DeptMapper.class);
	private RoleMapper roleMapper = SpringContextHolder.getBean(RoleMapper.class);
	
	@Override
	public String getOrgNameById(Long autoId) {
		OrgOrganization organization = orgOrganizationMapper.selectByPrimaryKey(autoId);
		if(organization != null) {
			return organization.getTitle();
		}
		return null;
	}

	@Override
	public String getParentOrgNameByParentId(Long parentId) {
		OrgOrganization organization = orgOrganizationMapper.selectByParentId(parentId);
		if(organization != null) {
			return organization.getTitle();
		}
		return null;
	}

	@Override
	public String getUserNameById(Long userId) {
		User user = userMapper.selectByPrimaryKey(userId);
		if(user != null) {
			return user.getName();
		}
		return null;
	}

	@Override
	public List<Long> getSubDeptId(Long deptId) {
		return deptMapper.selectSubDeptId(deptId);
	}

	@Override
	public String getSingleRoleTip(Long roleId) {
		Role role = roleMapper.selectByPrimaryKey(roleId);
		if(role != null) {
			return role.getTips();
		}else {
			return null;
		}
	}
}
