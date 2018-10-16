package com.zccoder.demo.spring.schema.support;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSimpleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * 标题：自定义 BeanDefinitionParser<br>
 * 描述：自定义 BeanDefinitionParser<br>
 * 时间：2018/10/16<br>
 *
 * @author zc
 **/
public class ServiceBeanDefinitionParser extends AbstractSimpleBeanDefinitionParser {

    @Override
    protected Class<?> getBeanClass(Element element) {
        return ServiceConfig.class;
    }



    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        String id = element.getAttribute("id");
        String serviceInterface = element.getAttribute("interface");
        String rel = element.getAttribute("rel");

        if (StringUtils.hasText(id)) {
            builder.addPropertyValue("id", id);
        }
        if (StringUtils.hasText(serviceInterface)) {
            builder.addPropertyValue("serviceInterface", serviceInterface);
        }
        if (StringUtils.hasText(rel)) {
            builder.addPropertyValue("rel", rel);
        }

    }
}
