package com.ysf.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import com.ysf.po.User;

@Mapper
@CacheConfig(cacheNames = {"user"})
public interface UserMapper {
	@CacheEvict(key = "#p0")
    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    @Cacheable(key = "#p0")
    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User selectByUserName(String userName);
}
