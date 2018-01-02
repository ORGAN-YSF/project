package com.ysf.po;

import java.util.Date;

public class User {
    private Long userId;

    private String avatar;

    private String account;

    private String password;

    private String salt;

    private String name;

    private Date birthday;

    private Integer sex;

    private String email;

    private String phone;

    private String roleId;

    private Long deptId;

    private Integer status;

    private Date createTime;

    private Integer version;

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
}