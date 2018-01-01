package com.ysf.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class GeneratorCodeApplication {
	public static void main(String[] args) {
		generatorCode("com.ysf", "my_order");
	}

	public static void generatorCode(String packageName,String... tableNames) {
		GlobalConfig globalConfig = new GlobalConfig();
		String dbUrl = "jdbc:mysql://127.0.0.1:3306/sysmgr";

		DataSourceConfig dataSourceConfig = new DataSourceConfig();
		dataSourceConfig.setDbType(DbType.MYSQL)
		.setUrl(dbUrl)
		.setUsername("root")
		.setPassword("123456")
		.setDriverName("com.mysql.jdbc.Driver");

		StrategyConfig strategyConfig = new StrategyConfig();
		strategyConfig.setCapitalMode(true)
		.setEntityLombokModel(false)
		.setDbColumnUnderline(true)
		.setNaming(NamingStrategy.underline_to_camel)
		.setInclude(tableNames);

		globalConfig.setActiveRecord(false)
		.setAuthor("sunwenxing")
		.setOutputDir("G:\\YSF\\project\\manager-center\\src\\main\\java")
		.setFileOverride(true);

		new AutoGenerator().setGlobalConfig(globalConfig)
		.setDataSource(dataSourceConfig)
		.setStrategy(strategyConfig)
		.setPackageInfo(
				new PackageConfig()
				.setParent(packageName)
				.setEntity("po")).execute();
	}
}
