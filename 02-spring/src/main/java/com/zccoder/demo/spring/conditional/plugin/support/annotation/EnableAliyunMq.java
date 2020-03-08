package com.zccoder.demo.spring.conditional.plugin.support.annotation;

import com.zccoder.demo.spring.conditional.plugin.support.configuration.AliyunMqConfiguration;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 开启AliyunMQ；当使用此注解时，会自动注册AliyunMQ生产者
 *
 * @author zc 2018-06-28
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AliyunMqConfiguration.class)
@Documented
public @interface EnableAliyunMq {

}
