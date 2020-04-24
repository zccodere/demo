package com.zccoder.demo.spring.aop.mark;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 启动类
 *
 * @author zc 2020-04-24
 */
@Configuration
@ComponentScan("com.zccoder.demo.spring.aop.mark")
@EnableAspectJAutoProxy
public class MarkApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MarkApplication.class);

        MyService myService = (MyService) context.getBean("myService");
        myService.doService();

        MethodHandler itemBatchGetHandler = (MethodHandler) context.getBean("itemBatchGetHandler");
        String aaa = itemBatchGetHandler.doService("aaa");
        System.out.println(aaa);

        MethodHandler itemBatchPutHandler = (MethodHandler) context.getBean("itemBatchPutHandler");
        String ccc = itemBatchPutHandler.doService("ccc");
        System.out.println(ccc);
    }
}
