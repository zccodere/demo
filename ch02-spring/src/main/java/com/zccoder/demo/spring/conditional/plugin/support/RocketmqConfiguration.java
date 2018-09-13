package com.zccoder.demo.spring.conditional.plugin.support;

import com.zccoder.demo.spring.conditional.plugin.producer.Producer;
import com.zccoder.demo.spring.conditional.plugin.producer.RedismqProducer;
import com.zccoder.demo.spring.conditional.plugin.producer.RocketmqProducer;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

@Configuration
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class RocketmqConfiguration {

    @Bean(name = "rocketmqProducer")
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public Producer scheduledAnnotationProcessor() {
        return new RocketmqProducer();
    }
}
