package com.ysf.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息出参BO
 */
public class UserRspBO implements Serializable {
	private static final long serialVersionUID = 8901828199087357108L;
	
	private Long userId;				//用户id

    private String avatar;				//头像

    private String account;				//账号

    private String password;			//密码

    private String salt;				//密码盐

    private String name;				//姓名

    private Date birthday;				//生日

    private Integer sex;				//性别

    private String email;				//email

    private String phone;				//手机号

    private String roleId;				//角色id

    private Long deptId;				//部门id

    private Integer status;				//状态

    private Date createTime;			//创建时间

    private Integer version;			//版本

	public Long getUserId() {
		return userId;
	}
	

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	

	public String getAvatar() {
		return avatar;
	}
	

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	

	public String getAccount() {
		return account;
	}
	

	public void setAccount(String account) {
		this.account = account;
	}
	

	public String getPassword() {
		return password;
	}
	

	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getSalt() {
		return salt;
	}
	

	public void setSalt(String salt) {
		this.salt = salt;
	}
	

	public String getName() {
		return name;
	}
	

	public void setName(String name) {
		this.name = name;
	}
	

	public Date getBirthday() {
		return birthday;
	}
	

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	

	public Integer getSex() {
		return sex;
	}
	

	public void setSex(Integer sex) {
		this.sex = sex;
	}
	

	public String getEmail() {
		return email;
	}
	

	public void setEmail(String email) {
		this.email = email;
	}
	

	public String getPhone() {
		return phone;
	}
	

	public void setPhone(String phone) {
		this.phone = phone;
	}
	

	public String getRoleId() {
		return roleId;
	}
	

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	

	public Long getDeptId() {
		return deptId;
	}
	

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	

	public Integer getStatus() {
		return status;
	}
	

	public void setStatus(Integer status) {
		this.status = status;
	}
	

	public Date getCreateTime() {
		return createTime;
	}
	

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	

	public Integer getVersion() {
		return version;
	}
	

	public void setVersion(Integer version) {
		this.version = version;
	}
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
