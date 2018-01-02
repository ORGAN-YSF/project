package com.ysf.common.constant.dictmap;

/**
 * 组织机构字典
 * @author sunwenxing
 */
public class OrganizationDict extends AbstractDictMap {
	@Override
	public void init() {
		put("autoId","组织机构");
		put("autoCode", "组织机构编码");
		put("parentId", "上级组织机构");
		put("title", "组织机构名称");
		put("alias", "组织机构别名");
		put("createTime", "创建时间");
		put("createUid", "创建人");
		put("updateTime", "更新时间");
		put("updateUid", "更新人");
	}

	@Override
	protected void initBeWrapped() {
		putFieldWrapperMethodName("autoId", "getOrgNameById");
		putFieldWrapperMethodName("parentId", "getParentOrgNameByParentId");
		putFieldWrapperMethodName("createUid", "getUserNameById");
		putFieldWrapperMethodName("updateUid", "getUserNameById");
	}
}
