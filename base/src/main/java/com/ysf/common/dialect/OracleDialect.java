package com.ysf.common.dialect;

public class OracleDialect extends Dialect{
	@Override
	public String getPageSQL(String sql, int offset, int limit) {
		StringBuffer pagingSelect = new StringBuffer();
		pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
		pagingSelect.append(sql.trim());
		pagingSelect.append(" ) row_ ) where rownum_ > ").append(offset);
		pagingSelect.append(" and rownum_ <= ").append(offset + limit);
		return pagingSelect.toString();
	}
}
