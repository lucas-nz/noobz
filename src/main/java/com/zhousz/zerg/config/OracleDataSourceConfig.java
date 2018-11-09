package com.zhousz.zerg.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

@Configuration
@MapperScan(basePackages = OracleDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "oracleSqlSessionFactory")
public class OracleDataSourceConfig {

	static final String PACKAGE = "com.zhousz.zerg.dao.oracle";

	static final String MAPPER_LOCATION = "classpath:mapper/oracle/*.xml";

	@Bean(name = "oracleDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.druid.oracle")
	@Primary
	public DataSource oracleDataSource() {
		return DruidDataSourceBuilder.create().build();
	}

	@Bean(name = "oracleTransactionManager")
	@Primary
	public DataSourceTransactionManager oracleTransactionManager() {
		return new DataSourceTransactionManager(oracleDataSource());
	}

	@Bean(name = "oracleSqlSessionFactory")
	@Primary
	public SqlSessionFactory oracleSqlSessionFactory(
			@Qualifier("oracleDataSource") DataSource dataSource)
			throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory
				.setMapperLocations(new PathMatchingResourcePatternResolver()
						.getResources(MAPPER_LOCATION));
		return sessionFactory.getObject();
	}

}
