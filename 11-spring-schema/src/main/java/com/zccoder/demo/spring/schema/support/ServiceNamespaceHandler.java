package com.zccoder.demo.spring.schema.support;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * 标题：自定义 NamespaceHandler<br>
 * 描述：自定义 NamespaceHandler<br>
 * 时间：2018/10/16<br>
 *
 * @author zc
 **/
public class ServiceNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("service", new ServiceBeanDefinitionParser());
    }
}
