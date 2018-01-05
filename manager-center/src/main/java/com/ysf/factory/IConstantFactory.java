package com.ysf.factory;

import java.util.List;

/**
 * 常量类生产工厂
 * @author sunwenxing
 */
public interface IConstantFactory {
	/**
	 * 根据组织机构id查询名称
	 */
	String getOrgNameById(Long autoId);

	/**
	 * 根据父级id查询父级名称
	 */
	String getParentOrgNameByParentId(Long parentId);
	
	/**
	 * 根据用户id查询用户名
	 */
	String getUserNameById(Long userId);

	/**
	 * 根据部门id获取子部门
	 */
	List<Long> getSubDeptId(Long deptId);

	String getSingleRoleTip(Long role);
}
