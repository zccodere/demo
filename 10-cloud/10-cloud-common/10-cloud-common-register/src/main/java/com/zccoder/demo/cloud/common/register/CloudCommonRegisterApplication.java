package com.zccoder.demo.cloud.common.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 标题：注册中心<br>
 * 描述：启动类<br>
 * 时间：2018/10/09<br>
 *
 * @author zc
 **/
@EnableEurekaServer
@SpringBootApplication
public class CloudCommonRegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudCommonRegisterApplication.class, args);
    }
}
