package com.zccoder.demo.spring.enable.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 注解 @Enable 学习启动类
 *
 * @author zc 2018-06-28
 **/
public class Start {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(PluginConfig.class);
        HelloService bean = context.getBean(HelloService.class);
        bean.hello("Spring Plugin");
    }
}
