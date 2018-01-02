package com.ysf.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ysf.po.OperationLog;

@Mapper
public interface OperationLogMapper {
    int deleteByPrimaryKey(Long operationId);

    int insert(OperationLog record);

    int insertSelective(OperationLog record);

    OperationLog selectByPrimaryKey(Long operationId);

    int updateByPrimaryKeySelective(OperationLog record);

    int updateByPrimaryKey(OperationLog record);
}