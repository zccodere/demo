package com.zccoder.demo.spring.enable.plugin.support;

import com.zccoder.demo.spring.enable.plugin.DemoPlugin;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 标题：插件配置类<br>
 * 描述：插件配置类<br>
 * 时间：2018/06/28<br>
 *
 * @author zc
 **/
public class PluginDemoConfiguration implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // 注册插件类
        String name = DemoPlugin.class.getName();
        BeanDefinition beanDefinition = new RootBeanDefinition();
        beanDefinition.setBeanClassName(name);
        registry.registerBeanDefinition(name, beanDefinition);

    }
}
