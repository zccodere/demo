package com.zccoder.demo.cloud.service.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 订单服务启动类
 *
 * @author zc 2018-10-12
 **/
@EnableDiscoveryClient
@SpringBootApplication
public class CloudServiceOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudServiceOrderApplication.class, args);
    }

}
