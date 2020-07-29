package com.zccoder.demo.spring.ordered;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 启动类
 *
 * @author zc
 * @date 2020/07/29
 */
@Configuration
@ComponentScan("com.zccoder.demo.spring.ordered")
public class Start {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Start.class);
        MessageService messageService = applicationContext.getBean(MessageService.class);
        messageService.doMessage("Hello");
    }

}
