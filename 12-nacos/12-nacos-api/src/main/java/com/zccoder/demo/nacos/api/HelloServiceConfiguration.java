package com.zccoder.demo.nacos.api;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.lang.NonNull;

/**
 * 自动注册Bean
 *
 * @author zc 2019-06-18
 */
@ComponentScan
@EnableFeignClients
@Configuration
public class HelloServiceConfiguration implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(@NonNull AnnotationMetadata annotationMetadata, BeanDefinitionRegistry registry) {
        String name = HelloServiceConfiguration.class.getName();
        BeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClassName(name);
        registry.registerBeanDefinition(name, beanDefinition);
    }
}
