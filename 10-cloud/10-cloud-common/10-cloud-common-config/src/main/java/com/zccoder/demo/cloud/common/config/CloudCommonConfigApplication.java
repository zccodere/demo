package com.zccoder.demo.cloud.common.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 配置中心启动类
 *
 * @author zc 2018-10-11
 **/
@EnableConfigServer
@EnableDiscoveryClient
@SpringBootApplication
public class CloudCommonConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudCommonConfigApplication.class, args);
    }

}
