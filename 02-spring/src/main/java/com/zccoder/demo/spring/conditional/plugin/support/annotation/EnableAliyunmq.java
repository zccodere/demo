package com.zccoder.demo.spring.conditional.plugin.support.annotation;

import com.zccoder.demo.spring.conditional.plugin.support.configuration.AliyunmqConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 标题：开启AliyunMQ<br>
 * 描述：当使用此注解时，会自动注册AliyunMQ生产者<br>
 * 时间：2018/06/28<br>
 *
 * @author zc
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AliyunmqConfiguration.class)
@Documented
public @interface EnableAliyunmq {



}
