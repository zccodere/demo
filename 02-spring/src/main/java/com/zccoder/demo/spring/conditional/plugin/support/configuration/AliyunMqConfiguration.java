package com.zccoder.demo.spring.conditional.plugin.support.configuration;

import com.zccoder.demo.spring.conditional.plugin.producer.AliyunMqProducer;
import com.zccoder.demo.spring.conditional.plugin.Producer;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

/**
 * 注册 AliyunMq；注册 AliyunMq
 *
 * @author zc 2018-06-28
 **/
@Configuration
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class AliyunMqConfiguration {

    @Bean(name = "aliyunMqProducer")
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public Producer scheduledAnnotationProcessor() {
        return new AliyunMqProducer();
    }
}
