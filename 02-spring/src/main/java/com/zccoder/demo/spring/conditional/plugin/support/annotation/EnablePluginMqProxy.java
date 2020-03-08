package com.zccoder.demo.spring.conditional.plugin.support.annotation;

import com.zccoder.demo.spring.conditional.plugin.support.configuration.PluginMqProxyConfiguration;
import com.zccoder.demo.spring.conditional.plugin.MqStrategy;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 开启MQ代理；根据条件生成指定类型的生产者
 *
 * @author zc 2018-07-24
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(PluginMqProxyConfiguration.class)
@Documented
public @interface EnablePluginMqProxy {

    MqStrategy value() default MqStrategy.RedisMQ;

}
