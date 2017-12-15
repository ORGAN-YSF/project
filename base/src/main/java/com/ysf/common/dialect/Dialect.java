package com.ysf.common.dialect;

/**
 * 数据库方言
 * @author sunwenxing
 */
public abstract class Dialect {
	public static enum Type {
		MYSQL, ORACLE
	}

	public abstract String getPageSQL(String sql,int skipResults,int maxResults);
}
