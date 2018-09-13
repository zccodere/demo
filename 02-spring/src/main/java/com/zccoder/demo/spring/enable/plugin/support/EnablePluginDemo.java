package com.zccoder.demo.spring.enable.plugin.support;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 标题：启动插件注解<br>
 * 描述：启动插件注解<br>
 * 时间：2018/06/28<br>
 *
 * @author zc
 **/
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(PluginDemoConfiguration.class)
public @interface EnablePluginDemo {

}
