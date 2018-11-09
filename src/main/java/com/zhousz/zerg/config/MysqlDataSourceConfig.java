package com.zhousz.zerg.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

@Configuration
@MapperScan(basePackages = MysqlDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "mysqlSqlSessionFactory")
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

	@Bean(name = "mysqlTransactionManager")
	public DataSourceTransactionManager mysqlTransactionManager() {
		return new DataSourceTransactionManager(mysqlDataSource());
	}

	@Bean(name = "mysqlSqlSessionFactory")
	public SqlSessionFactory mysqlSessionFactory(
			@Qualifier("mysqlDataSource") DataSource dataSource)
			throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		// 如果不使用xml的方式配置mapper，则可以省去下面这行mapper location的配置
		sessionFactory
				.setMapperLocations(new PathMatchingResourcePatternResolver()
						.getResources(MysqlDataSourceConfig.MAPPER_LOCATION));
		return sessionFactory.getObject();
	}

}
