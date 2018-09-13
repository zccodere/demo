package com.zccoder.demo.spring.conditional.busi;

import com.zccoder.demo.spring.conditional.plugin.Producer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * 标题：@Enable 学习启动类<br>
 * 描述：@Enable 学习启动类<br>
 * 时间：2018/06/28<br>
 *
 * @author zc
 **/
public class Start {

    public static void main(String[] args) {
        startByJava();
        startByXml();
    }

    private static void startByXml() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        Map<String, Producer> producerMap = applicationContext.getBeansOfType(Producer.class);
        for (Map.Entry<String, Producer> entry : producerMap.entrySet()) {
            System.out.println(entry.getKey());
            entry.getValue().hello("xml message");
        }
    }

    private static void startByJava() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(PluginConfig.class);
        Map<String, Producer> producerMap = applicationContext.getBeansOfType(Producer.class);
        for (Map.Entry<String, Producer> entry : producerMap.entrySet()) {
            System.out.println(entry.getKey());
            entry.getValue().hello("java message");
        }
    }
}
