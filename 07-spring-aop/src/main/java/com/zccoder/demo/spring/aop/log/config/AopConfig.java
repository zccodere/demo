package com.zccoder.demo.spring.aop.log.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 配置
 * // 使用@EnableAspectJAutoProxy注解开启Spring对AspectJ的支持。
 *
 * @author ZhangCheng
 * @version V1.0
 * @date 2017-03-20
 */
@Configuration
@ComponentScan("com.zccoder.demo.spring.aop.log")
@EnableAspectJAutoProxy
public class AopConfig {

    public AopConfig() {
        System.out.println("AopConfig执行");
    }

}
