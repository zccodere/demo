package com.zccoder.demo.cloud.service.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 标题：订单服务<br>
 * 描述：订单服务<br>
 * 时间：2018/10/12<br>
 *
 * @author zc
 **/
@EnableDiscoveryClient
@SpringBootApplication
public class CloudServiceOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudServiceOrderApplication.class, args);
    }

}
