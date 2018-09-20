package com.zccoder.demo.dubbo.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author zc
 * @version 1.0 2017-09-15
 * @title 启动类
 * @describe 启动类
 */
@SpringBootApplication
@ImportResource("classpath:dubboProviderConfig.xml")
@EnableJpaRepositories("com.zccoder.demo.dubbo.dao")
@EntityScan("com.zccoder.demo.dubbo.dao")
@ComponentScan("com.zccoder.demo.dubbo")
public class DemoDubboServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoDubboServiceApplication.class, args);
	}
}
