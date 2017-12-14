package com.ysf.config;

import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.alibaba.druid.pool.DruidDataSource;
import com.ysf.config.properties.DruidProperties;

@Configuration
public class DruidDataSourceConfig {
	@Autowired
	private DruidProperties properties;

	@Primary
	@Bean(initMethod = "init", destroyMethod = "close")
	public DruidDataSource dataSource() throws SQLException {
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setDriverClassName(properties.getDriverClassName());
		druidDataSource.setUrl(properties.getUrl());
		druidDataSource.setUsername(properties.getUsername());
		druidDataSource.setPassword(properties.getPassword());
		druidDataSource.setInitialSize(properties.getMinIdle());
		druidDataSource.setMinIdle(properties.getMinIdle());
		druidDataSource.setMaxActive(properties.getMaxActive());
		druidDataSource.setMaxWait(properties.getMaxWait());
		druidDataSource.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());
		druidDataSource.setMinEvictableIdleTimeMillis(properties.getMinEvictableIdleTimeMillis());
		druidDataSource.setValidationQuery(properties.getValidationQuery());
		druidDataSource.setTestWhileIdle(properties.getTestWhileIdle());
		druidDataSource.setTestOnBorrow(properties.getTestOnBorrow());
		druidDataSource.setTestOnReturn(properties.getTestOnReturn());
		druidDataSource.setPoolPreparedStatements(properties.getPoolPreparedStatements());
		druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(properties.getMaxPoolPreparedStatementPerConnectionSize());
		druidDataSource.setFilters(properties.getFilters());
		return druidDataSource;
	}
}
