package com.ysf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ysf.config.properties.YsfProperties;

@SpringBootApplication
public class ManagerApplication extends WebMvcConfigurerAdapter {
	private static Logger log = LoggerFactory.getLogger(ManagerApplication.class);
	
	@Autowired
	private YsfProperties ysfProperties;
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if(ysfProperties.getSwaggerOpen()) {
			registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
			registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
		}
	}
	
    
	
	public static void main(String[] args) {
		SpringApplication.run(ManagerApplication.class, args);
		log.debug("系统启动成功");	
	}
}
