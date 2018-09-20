package com.zccoder.demo.dubbo.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author zc
 * @version 1.0 2017-09-15
 * @title 启动类
 * @describe 启动类
 */
@SpringBootApplication
@ImportResource("classpath*:dubboConsumerConfig.xml")
public class DemoDubboRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoDubboRestApplication.class, args);
	}
}
