package com.ysf.factory;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.ysf.common.util.SpringContextHolder;
import com.ysf.dao.OrgOrganizationMapper;

@Component
@DependsOn("springContextHolder")
public class ConstantFactory implements IConstantFactory {
	public static IConstantFactory me() {
        return SpringContextHolder.getBean("constantFactory");
    }
	
	private OrgOrganizationMapper orgOrganizationMapper = SpringContextHolder.getBean(OrgOrganizationMapper.class);
	
	
	@Override
	public String getOrgNameById(Long autoId) {
		return null;
	}

	@Override
	public String getParentOrgNameByParentId(Long parentId) {
		return null;
	}

	@Override
	public String getUserNameById(Long userId) {
		return null;
	}
	
	
}
