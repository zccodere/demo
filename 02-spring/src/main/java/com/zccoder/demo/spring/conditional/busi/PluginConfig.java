package com.zccoder.demo.spring.conditional.busi;

import com.zccoder.demo.spring.conditional.plugin.support.annotation.EnablePluginMqProxy;
import com.zccoder.demo.spring.conditional.plugin.support.annotation.EnableRocketmq;
import com.zccoder.demo.spring.conditional.plugin.MqStrategy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 标题：@Enable 配置类<br>
 * 描述：@Enable 配置类<br>
 * 时间：2018/06/28<br>
 *
 * @author zc
 **/
@ComponentScan("com.zccoder.demo.spring.conditional.busi")
@EnableRocketmq
@EnablePluginMqProxy(MqStrategy.RedisMQ)
@Configuration
public class PluginConfig {
}
