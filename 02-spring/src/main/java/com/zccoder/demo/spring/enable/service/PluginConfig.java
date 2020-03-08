package com.zccoder.demo.spring.enable.service;

import com.zccoder.demo.spring.enable.plugin.support.EnablePluginDemo;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 注解 @Enable 配置类
 *
 * @author zc 2018-06-28
 **/
@EnablePluginDemo
@Configuration
@ComponentScan("com.zccoder.demo.spring.enable.service")
public class PluginConfig {
}
