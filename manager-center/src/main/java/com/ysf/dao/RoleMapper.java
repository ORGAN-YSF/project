package com.ysf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ysf.po.Role;

@Mapper
public interface RoleMapper {
    int deleteByPrimaryKey(Long roleId);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

	List<String> selectByRoleIds(List<Long> list);
}