package com.ysf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ysf.common.page.Page;
import com.ysf.po.OrgOrganization;

@Mapper
public interface OrgOrganizationMapper {
    int deleteByPrimaryKey(Long autoId);

    int insert(OrgOrganization record);

    int insertSelective(OrgOrganization record);

    OrgOrganization selectByPrimaryKey(Long autoId);

    int updateByPrimaryKeySelective(OrgOrganization record);

    int updateByPrimaryKey(OrgOrganization record);

	List<OrgOrganization> selectOrgListPage(String orgName, Page<OrgOrganization> page);
}
