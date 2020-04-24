package com.zccoder.demo.redis.rate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author zc 2020-04-24
 */
@SpringBootApplication
public class RedisRateLimiterApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisRateLimiterApplication.class);
    }

}
