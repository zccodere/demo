package com.zccoder.demo.spring.enable.busi;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 标题：@Enable 学习启动类<br>
 * 描述：@Enable 学习启动类<br>
 * 时间：2018/06/28<br>
 *
 * @author zc
 **/
public class Start {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(PluginConfig.class);
        HelloService bean = context.getBean(HelloService.class);
        bean.hello("Spring Plugin");
    }
}
