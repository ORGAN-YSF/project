package com.ysf.common.bo;

import java.io.Serializable;
import java.util.List;

/**
 * 用户更多信息
 * @author sunwenxing
 */
public class UserInfo implements Serializable {
	private static final long serialVersionUID = -5622799900150875772L;
	
	private Long userId;					//用户id
	
	private String account;					//账号
	
	private String userName;				//用户名
	
	public Long deptId;      				//部门id
    
	public List<Long> roleList; 			//角色集
    
	public String deptName;        			//部门名称
    
	public List<String> roleNames; 			//角色名称集

	public Long getUserId() {
		return userId;
	}
	

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	

	public String getAccount() {
		return account;
	}
	

	public void setAccount(String account) {
		this.account = account;
	}
	

	public String getUserName() {
		return userName;
	}
	

	public void setUserName(String userName) {
		this.userName = userName;
	}
	

	public Long getDeptId() {
		return deptId;
	}
	

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	

	public List<Long> getRoleList() {
		return roleList;
	}
	

	public void setRoleList(List<Long> roleList) {
		this.roleList = roleList;
	}
	

	public String getDeptName() {
		return deptName;
	}
	

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	

	public List<String> getRoleNames() {
		return roleNames;
	}
	

	public void setRoleNames(List<String> roleNames) {
		this.roleNames = roleNames;
	}
}
