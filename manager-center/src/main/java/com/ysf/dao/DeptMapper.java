package com.ysf.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ysf.po.Dept;

@Mapper
public interface DeptMapper {
    int deleteByPrimaryKey(Long deptId);

    int insert(Dept record);

    int insertSelective(Dept record);

    Dept selectByPrimaryKey(Long deptId);

    int updateByPrimaryKeySelective(Dept record);

    int updateByPrimaryKey(Dept record);
}