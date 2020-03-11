package com.zccoder.demo.cloud.common.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 注册中心启动类
 *
 * @author zc 2018-10-09
 **/
@EnableEurekaServer
@SpringBootApplication
public class CloudCommonRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudCommonRegisterApplication.class, args);
    }
}
