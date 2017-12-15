package com.ysf.common.page;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;

import com.ysf.common.dialect.Dialect;
import com.ysf.common.dialect.MysqlDialect;
import com.ysf.common.dialect.OracleDialect;

@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class,Integer.class })})
public class PaginationInterceptor implements Interceptor {
	public Object intercept(Invocation invocation) throws Throwable {
		Object target = invocation.getTarget();

		if (target instanceof StatementHandler) {
			StatementHandler statementHandler = (StatementHandler) target;
			MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);

			RowBounds rowBounds = (RowBounds) metaStatementHandler.getValue("delegate.rowBounds");
			if (rowBounds == null || rowBounds == RowBounds.DEFAULT) {
				return invocation.proceed();
			}
			
			Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");
			Dialect.Type databaseType = null;
			try {
				databaseType = Dialect.Type.valueOf(configuration.getVariables().getProperty("dialect").toUpperCase());
			} catch (Exception e) {
			}
			if (databaseType == null) {
				throw new RuntimeException("the value of the dialect property in configuration.xml is not defined:" + configuration.getVariables().getProperty("dialect"));
			}
			Dialect dialect = null;
			switch (databaseType) {
				case ORACLE:
					dialect = new OracleDialect();
					break;
				case MYSQL:
					dialect = new MysqlDialect();
					break;
				default:
					throw new RuntimeException("the value of the dialect property in configuration.xml is not defined : "+ configuration.getVariables().getProperty("dialect"));
			}
			
			BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
			String originalSql = (String) boundSql.getSql();
			
			MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
			Connection connection = (Connection) invocation.getArgs()[0];
			Pagination page = this.count(originalSql, connection, mappedStatement, boundSql, (Pagination) rowBounds);
			
			metaStatementHandler.setValue("delegate.rowBounds.offset",RowBounds.NO_ROW_OFFSET);
			metaStatementHandler.setValue("delegate.rowBounds.limit",RowBounds.NO_ROW_LIMIT);
			metaStatementHandler.setValue("delegate.boundSql.sql", dialect.getPageSQL(originalSql, page.getCurrentOffset(), page.getPageSize()));
		}
		
		return invocation.proceed();
	}
	
	/**
	 * 查询总记录条数
	 * 
	 * @param sql
	 * @param connection
	 * @param mappedStatement
	 * @param boundSql
	 * @param page
	 */
	public Pagination count(String sql, Connection connection, MappedStatement mappedStatement, BoundSql boundSql,Pagination page) {
		String sqlUse = sql;
		int orderByIndex = sql.toUpperCase().lastIndexOf("ORDER BY");
		if ( orderByIndex > -1) {
			sqlUse = sql.substring(0, orderByIndex);
		}
		StringBuffer countSql = new StringBuffer("SELECT COUNT(1) FROM (");
		countSql.append(sqlUse).append(") AS TOTAL");
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			preparedStatement = connection.prepareStatement(countSql.toString());
			BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql.toString(),
					boundSql.getParameterMappings(), boundSql.getParameterObject());
			ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement,
					boundSql.getParameterObject(), countBS);
			parameterHandler.setParameters(preparedStatement);
			resultSet = preparedStatement.executeQuery();
			int totalRecord = 0;
			if (resultSet.next()) {
				totalRecord = resultSet.getInt(1);
			}
			page.setTotalRecord(totalRecord);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
			    if(resultSet!=null){
			        resultSet.close();
			    }
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return page;
	}

	public Object plugin(Object target) {
		if (target instanceof StatementHandler) {
			return Plugin.wrap(target, this);
		}
		return target;
	}

	public void setProperties(Properties arg0) {

	}
}
