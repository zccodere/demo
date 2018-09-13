package com.zccoder.demo.spring.conditional.plugin.support.configuration;

import com.zccoder.demo.spring.conditional.plugin.MqStrategy;
import com.zccoder.demo.spring.conditional.plugin.support.annotation.EnablePluginMqProxy;
import com.zccoder.demo.spring.conditional.plugin.producer.RedismqProducer;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 标题：根据条件动态注册Bean<br>
 * 描述：根据条件动态注册Bean<br>
 * 时间：2018/07/24<br>
 *
 * @author zc
 **/
public class PluginMqProxyConfiguration implements ImportBeanDefinitionRegistrar{

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Object value = importingClassMetadata.getAnnotationAttributes(EnablePluginMqProxy.class.getName()).get("value");
        MqStrategy strategy = (MqStrategy)value;
        System.out.println(strategy);
        BeanDefinition producer = new RootBeanDefinition(RedismqProducer.class);
        registry.registerBeanDefinition(RedismqConfiguration.REDISMQ_ANNOTATION_BEAN_NAME,producer);
    }
}
