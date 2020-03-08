package com.zccoder.demo.spring.factory;

import com.zccoder.demo.spring.factory.config.SpringConfig;
import com.zccoder.demo.spring.factory.platform.ClientBean;
import com.zccoder.demo.spring.factory.proxy.ProxyProducer;
import com.zccoder.demo.spring.factory.service.UserService;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;
import java.util.Set;

/**
 * FactoryBean 学习启动类
 *
 * @author zc 2018-06-28
 **/
public class FactoryProducerStart {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService userService = applicationContext.getBean(UserService.class);
        userService.create("Hello");
        userService.delete("Hello");
        userService.query("Hello");

        Map<String, ProxyProducer> producerMap = applicationContext.getBeansOfType(ProxyProducer.class);
        Set<String> keySet = producerMap.keySet();
        for (String key : keySet) {
            System.out.println("Bean ID[" + key + "]；Value[" + producerMap.get(key) + "]");
        }

        System.out.println("----------------------------------------");

        ClientBean clientBean = applicationContext.getBean(ClientBean.class);
        clientBean.doTest();

    }
}
