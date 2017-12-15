package com.ysf.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.ysf.common.page.PaginationInterceptor;

@Configuration
@EnableTransactionManagement	//开启注解事务
@MapperScan(basePackages = {"com.ysf"},annotationClass = Mapper.class)
@PropertySource("classpath:mybatis.properties")
public class MyBatisConfig {
	@Value("${mybatis.mapper.xml.path}")
	private String xmlPath;
	@Value("${mybatis.dialect}")
	private String dialect;
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(xmlPath));
		sqlSessionFactoryBean.setConfigurationProperties(properties());
		sqlSessionFactoryBean.setPlugins(new Interceptor[]{paginationInterceptor()});
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}

	@Bean
	public Properties properties() {
		Properties properties = new Properties();
		properties.setProperty("dialect", dialect);
		return properties;
	}
}
