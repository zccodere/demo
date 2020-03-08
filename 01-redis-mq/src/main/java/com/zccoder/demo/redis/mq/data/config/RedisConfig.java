package com.zccoder.demo.redis.mq.data.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**
 * Redis 配置类
 *
 * @author zc 2020-03-08
 */
@Configuration
public class RedisConfig {

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        // Spring Boot 2.X 开始默认使用 Lettuce
        return new LettuceConnectionFactory();
    }
}