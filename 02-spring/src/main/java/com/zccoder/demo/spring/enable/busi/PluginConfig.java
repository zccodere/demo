package com.zccoder.demo.spring.enable.busi;

import com.zccoder.demo.spring.enable.plugin.support.EnablePluginDemo;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 标题：@Enable 配置类<br>
 * 描述：@Enable 配置类<br>
 * 时间：2018/06/28<br>
 *
 * @author zc
 **/
@EnablePluginDemo
@Configuration
@ComponentScan("com.zccoder.demo.spring.enable.busi")
public class PluginConfig {
}
