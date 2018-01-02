package com.ysf.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * 组织机构保存入参BO
 */
public class OrganizationSaveReqBO implements Serializable {
	private static final long serialVersionUID = -2694219875302223580L;

	private String autoCode;					//编号
	
	private Long parentId;						//上级组织
	
	private String title;						//名称
	
	private String alias;						//别名
	
	private Date createTime;					//创建时间
	
	private Long createUid;						//创建人
	
	private Date updateTime;					//更新时间
	
	private Long updateUid;						//修改人

	public String getAutoCode() {
		return autoCode;
	}
	

	public void setAutoCode(String autoCode) {
		this.autoCode = autoCode;
	}
	

	public Long getParentId() {
		return parentId;
	}
	

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	

	public String getTitle() {
		return title;
	}
	

	public void setTitle(String title) {
		this.title = title;
	}
	

	public String getAlias() {
		return alias;
	}
	

	public void setAlias(String alias) {
		this.alias = alias;
	}
	

	public Date getCreateTime() {
		return createTime;
	}
	

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	

	public Long getCreateUid() {
		return createUid;
	}
	

	public void setCreateUid(Long createUid) {
		this.createUid = createUid;
	}
	

	public Date getUpdateTime() {
		return updateTime;
	}
	

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	

	public Long getUpdateUid() {
		return updateUid;
	}
	

	public void setUpdateUid(Long updateUid) {
		this.updateUid = updateUid;
	}
}
