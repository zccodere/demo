package com.zccoder.demo.retry.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.RetryListener;
import org.springframework.retry.annotation.EnableRetry;

/**
 * 重试配置类
 *
 * @author zc
 * @date 2020/05/07
 */
@Configuration
@EnableRetry
public class RetryConfiguration {

    @Bean
    public RetryListener retryListener1() {
        return new MyRetryListener();
    }

}
