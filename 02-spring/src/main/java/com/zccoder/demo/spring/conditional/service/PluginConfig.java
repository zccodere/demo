package com.zccoder.demo.spring.conditional.service;

import com.zccoder.demo.spring.conditional.plugin.support.annotation.EnablePluginMqProxy;
import com.zccoder.demo.spring.conditional.plugin.support.annotation.EnableRocketMq;
import com.zccoder.demo.spring.conditional.plugin.MqStrategy;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 注解 @Enable 配置类
 *
 * @author zc 2018-06-28
 **/
@ComponentScan("com.zccoder.demo.spring.conditional.service")
@EnableRocketMq
@EnablePluginMqProxy(MqStrategy.RedisMQ)
@Configuration
public class PluginConfig {

}
