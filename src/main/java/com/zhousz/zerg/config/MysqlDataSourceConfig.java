package com.zhousz.zerg.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

@Configuration
public class MysqlDataSourceConfig {

	// dao
	static final String PACKAGE = "com.zhousz.zerg.dao.mysql";
	
	// mapper
	static final String MAPPER_LOCATION = "classpath:mapper/mysql/*.xml";
	
	@Bean(name = "mysqlDataSource")
	@ConfigurationProperties("spring.datasource.druid.mysql")
	public DataSource mysqlDataSource() {
		return DruidDataSourceBuilder.create().build();
	}
	
	@Bean(name = "mysqTransactionManager")
	public DataSourceTransactionManager mysqlTransactionManager(){
		return new DataSourceTransactionManager(mysqlDataSource());
	}
	
	
	
	
	
	
	
	
}
