package com.ysf.dao;

import org.apache.ibatis.annotations.Mapper;
import com.ysf.po.User;

/**
 * Spring提供了四种规则来声明缓存规则:
 * @Cacheable表明在调用方法之前，首先应该在缓存中查询方法的返回值，
 * 如果这个值能够找到，则会返回缓存的值，否则执行该方法，并将返回值放到缓存中
 * @CachePut表明在方法调用钱不会检查缓存，方法始终都会被调用，调用之后
 * 把结果放到缓存中
 * @CacheEvict表明spring会消除一个或者多个缓存
 * @Caching分组的注解，可以同时应用多个其他缓存注解，可以相同类型或者不同类型
 */

/**
 * value：缓存名称
 * key：缓存的key值
 * Condition: SpEL表达式，如果得出的值是false,不会将缓存应用到方法调用上
 * Unless: SpEL表达式，如果得出的值是true,返回值不会放到缓存中
 * Condition是调用前检查，Unless是调用后对结果的检查
 */

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Long userId);
    
    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User selectByUserName(String userName);
}
