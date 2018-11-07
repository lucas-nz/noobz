package com.zhousz.zerg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.zhousz.zerg.dao")
@EnableTransactionManagement // 在service类中加入事务注解
public class ZergApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZergApplication.class, args);
	}
}
