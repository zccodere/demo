package com.zccoder.demo.spring.conditional.plugin.support.configuration;

import com.zccoder.demo.spring.conditional.plugin.Producer;
import com.zccoder.demo.spring.conditional.plugin.producer.RocketMqProducer;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

/**
 * 注册RocketMq；注册RocketMq
 *
 * @author zc 2018-06-28
 **/
@Configuration
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class RocketMqConfiguration {

    @Bean(name = "rocketMqProducer")
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public Producer scheduledAnnotationProcessor() {
        return new RocketMqProducer();
    }
}
