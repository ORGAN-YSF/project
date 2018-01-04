package com.ysf.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ysf.po.Relation;

@Mapper
public interface RelationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Relation record);

    int insertSelective(Relation record);

    Relation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Relation record);

    int updateByPrimaryKey(Relation record);
}