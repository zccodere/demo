package com.zccoder.demo.spring.conditional.plugin.support.configuration;

import com.zccoder.demo.spring.conditional.plugin.Producer;
import com.zccoder.demo.spring.conditional.plugin.producer.RedisMqProducer;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

/**
 * 注册RedisMq；注册RedisMq
 *
 * @author zc 2018-06-28
 **/
@Configuration
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class RedisMqConfiguration {

    public static final String REDIS_MQ_ANNOTATION_BEAN_NAME = "redisMqProducer";

    @Bean(name = REDIS_MQ_ANNOTATION_BEAN_NAME)
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public Producer scheduledAnnotationProcessor() {
        return new RedisMqProducer();
    }
}
