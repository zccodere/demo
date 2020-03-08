package com.zccoder.demo.spring.conditional.plugin.support.configuration;

import com.zccoder.demo.spring.conditional.plugin.MqStrategy;
import com.zccoder.demo.spring.conditional.plugin.producer.RedisMqProducer;
import com.zccoder.demo.spring.conditional.plugin.support.annotation.EnablePluginMqProxy;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.lang.NonNull;

import java.util.Map;
import java.util.Objects;

/**
 * 根据条件动态注册Bean；根据条件动态注册Bean
 *
 * @author zc 2018-07-24
 **/
public class PluginMqProxyConfiguration implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, @NonNull BeanDefinitionRegistry registry) {
        Map<String, Object> attributes = importingClassMetadata.getAnnotationAttributes(EnablePluginMqProxy.class.getName());
        if (Objects.isNull(attributes)) {
            return;
        }

        Object value = attributes.getOrDefault("value", MqStrategy.RedisMQ);
        MqStrategy strategy = (MqStrategy) value;
        System.out.println(strategy);
        BeanDefinition producer = new RootBeanDefinition(RedisMqProducer.class);
        registry.registerBeanDefinition(RedisMqConfiguration.REDIS_MQ_ANNOTATION_BEAN_NAME, producer);
    }
}
