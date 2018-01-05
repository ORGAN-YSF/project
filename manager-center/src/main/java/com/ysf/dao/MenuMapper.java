package com.ysf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.ysf.po.Menu;

@Mapper
public interface MenuMapper {
    int deleteByPrimaryKey(Long menuId);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Long menuId);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
    
    //根据角色id查询路径
	List<String> selectUrlsByRoleId(Long roleId);
}