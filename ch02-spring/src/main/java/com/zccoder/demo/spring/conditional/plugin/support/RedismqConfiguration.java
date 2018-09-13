package com.zccoder.demo.spring.conditional.plugin.support;

import com.zccoder.demo.spring.conditional.plugin.producer.Producer;
import com.zccoder.demo.spring.conditional.plugin.producer.RedismqProducer;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

@Configuration
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class RedismqConfiguration {

    public static final String REDISMQ_ANNOTATION_BEAN_NAME = "redismqProducer";

    @Bean(name = REDISMQ_ANNOTATION_BEAN_NAME)
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public Producer scheduledAnnotationProcessor() {
        return new RedismqProducer();
    }
}
