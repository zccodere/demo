package com.zccoder.demo.spring.aop.notnull;

import com.zccoder.demo.spring.aop.notnull.aspect.RespMessage;
import com.zccoder.demo.spring.aop.notnull.config.SpringConfig;
import com.zccoder.demo.spring.aop.notnull.service.IntfService;
import com.zccoder.demo.spring.aop.notnull.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 启动入口类
 *
 * @author ZhangCheng
 * @version V1.0
 * @date 2017-03-20
 */
public class NotNullMain {

    public static void main(String[] args) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        UserService userService = (UserService) context.getBean("UserService");

        RespMessage returnMessage = new RespMessage();

        returnMessage = userService.getUserInfoByName("NAME", "NAME");
        System.out.println("getUserInfoByName：" + returnMessage);

        returnMessage = userService.getUserInfoById("ID", "");
        System.out.println("getUserInfoById：" + returnMessage);

        IntfService intfService = (IntfService) context.getBean("IntfService");
        String uid = "";
        String uname = "name";
        String upaswd = "null";
        String email = "";
        returnMessage = intfService.getUserInfo(uid, uname, upaswd, email);

        System.out.println("intfService：" + returnMessage);
//        context.close();

    }

}
