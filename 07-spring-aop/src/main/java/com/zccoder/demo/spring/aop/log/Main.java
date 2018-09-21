package com.zccoder.demo.spring.aop.log;

import com.zccoder.demo.spring.aop.log.config.AopConfig;
import com.zccoder.demo.spring.aop.log.dto.ReturnMessage;
import com.zccoder.demo.spring.aop.log.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


/**
 * 启动
 *
 * @author ZhangCheng
 * @version V1.0
 * @date 2017-03-20
 */
public class Main {

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);

        UserService userService = (UserService) context.getBean("UserService");

        ReturnMessage returnMessage = userService.getUserInfoById("ID", "ID");
        System.out.println("getUserInfoById：" + returnMessage);

        returnMessage = userService.getUserInfoByName("NAME", "NAME");
        System.out.println("getUserInfoByName：" + returnMessage);

        context.close();
    }
}
