package com.ysf.common.dialect;

public class MysqlDialect extends Dialect{
	@Override
	public String getPageSQL(String sql, int offset, int limit) {
		StringBuffer pagingSelect = new StringBuffer();
		pagingSelect.append(sql);
		pagingSelect.append(" limit ");
		pagingSelect.append(offset);
		pagingSelect.append(",");
		pagingSelect.append(limit);
		return pagingSelect.toString();
	}
}
