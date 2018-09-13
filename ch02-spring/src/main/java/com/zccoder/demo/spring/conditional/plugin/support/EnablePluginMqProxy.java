package com.zccoder.demo.spring.conditional.plugin.support;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 标题：开启MQ代理<br>
 * 描述：根据条件生成指定类型的生产者<br>
 * 时间：2018/07/24<br>
 *
 * @author zc
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(PluginMqProxyConfiguration.class)
@Documented
public @interface EnablePluginMqProxy {

    Strategy value() default Strategy.RedisMQ;

}
