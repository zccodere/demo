package com.zccoder.demo.spring.aop.notnull.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Spring配置类
 *
 * @author ZhangCheng
 * @version V1.0
 * @date 2017-03-20
 */
@Configuration
@ComponentScan("com.zccoder.demo.spring.aop.notnull")
@EnableAspectJAutoProxy
public class SpringConfig {

}
