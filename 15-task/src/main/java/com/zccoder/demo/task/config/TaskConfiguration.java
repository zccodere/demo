package com.zccoder.demo.task.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 线程池配置类
 *
 * @author zc 2019-11-29
 */
@Configuration
public class TaskConfiguration {

    static final String MY_TASK_EXECUTOR_BEAN_NAME = "myTaskExecutor";

    @Bean(name = MY_TASK_EXECUTOR_BEAN_NAME)
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("myTaskExecutor");
        executor.setMaxPoolSize(1);
        executor.setCorePoolSize(1);
        executor.setDaemon(true);
        return executor;
    }
}
