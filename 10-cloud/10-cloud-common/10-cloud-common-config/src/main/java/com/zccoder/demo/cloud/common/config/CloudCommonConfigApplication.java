package com.zccoder.demo.cloud.common.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 标题：配置中心<br>
 * 描述：启动类<br>
 * 时间：2018/10/11<br>
 *
 * @author zc
 **/
@EnableConfigServer
@EnableDiscoveryClient
@SpringBootApplication
public class CloudCommonConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudCommonConfigApplication.class, args);
    }

}
