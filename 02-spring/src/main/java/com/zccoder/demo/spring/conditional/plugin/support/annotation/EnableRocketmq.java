package com.zccoder.demo.spring.conditional.plugin.support.annotation;

import com.zccoder.demo.spring.conditional.plugin.support.configuration.RocketmqConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 标题：开启RocketMQ<br>
 * 描述：当使用此注解时，会自动注册RocketMQ生产者<br>
 * 时间：2018/06/28<br>
 *
 * @author zc
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(RocketmqConfiguration.class)
@Documented
public @interface EnableRocketmq {



}
