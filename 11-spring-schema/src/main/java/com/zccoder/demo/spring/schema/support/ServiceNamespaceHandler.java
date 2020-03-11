package com.zccoder.demo.spring.schema.support;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * 自定义 NamespaceHandler
 *
 * @author zc 2018-10-16
 **/
public class ServiceNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("service", new ServiceBeanDefinitionParser());
    }
}
