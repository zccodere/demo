package com.zccoder.demo.nacos.consumer;

import com.zccoder.demo.nacos.api.EnableHelloService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 启动类
 *
 * @author zc 2019-06-18
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHelloService
public class NacosConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerApplication.class, args);
    }
}
