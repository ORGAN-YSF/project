package com.ysf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ManagerApplication {
	private static Logger log = LoggerFactory.getLogger(ManagerApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(ManagerApplication.class, args);
		log.debug("系统启动成功");	
	}
}
