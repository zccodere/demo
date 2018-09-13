package com.zccoder.demo.spring.conditional.busi;

import com.zccoder.demo.spring.conditional.plugin.producer.Producer;
import com.zccoder.demo.spring.conditional.plugin.support.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Map;

@ComponentScan("com.zccoder.demo.spring.conditional.busi")
//@EnableRedismq
//@EnableAliyunmq
@EnableRocketmq
@EnablePluginMqProxy(Strategy.RedisMQ)
@Configuration
public class Start {

    public static void main(String[] args) {
        startByJava();
//        startByXml();
    }

    private static void startByXml() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        Map<String, Producer> producerMap = applicationContext.getBeansOfType(Producer.class);
        for (String key : producerMap.keySet()) {
            System.out.println(key);
        }
    }

    private static void startByJava() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Start.class);
        Map<String, Producer> producerMap = applicationContext.getBeansOfType(Producer.class);
        for (String key : producerMap.keySet()) {
            System.out.println(key);
            producerMap.get(key).hello("message");
        }
    }
}
