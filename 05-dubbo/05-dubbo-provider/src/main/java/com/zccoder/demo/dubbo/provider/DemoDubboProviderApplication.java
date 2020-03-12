package com.zccoder.demo.dubbo.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 启动类
 *
 * @author zc 2017-09-15
 */
@EnableJpaRepositories("com.zccoder.demo.dubbo.provider.dao")
@EntityScan("com.zccoder.demo.dubbo.provider.dao")
@EnableDubbo
@SpringBootApplication
public class DemoDubboProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoDubboProviderApplication.class, args);
    }
}
