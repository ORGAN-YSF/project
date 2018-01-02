package com.ysf.factory;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.ysf.common.util.SpringContextHolder;
import com.ysf.dao.OrgOrganizationMapper;
import com.ysf.dao.UserMapper;
import com.ysf.po.OrgOrganization;
import com.ysf.po.User;

@Component
@DependsOn("springContextHolder")
public class ConstantFactory implements IConstantFactory {
	public static IConstantFactory me() {
        return SpringContextHolder.getBean("constantFactory");
    }
	
	private OrgOrganizationMapper orgOrganizationMapper = SpringContextHolder.getBean(OrgOrganizationMapper.class);
	private UserMapper userMapper = SpringContextHolder.getBean(UserMapper.class);
	
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
}
