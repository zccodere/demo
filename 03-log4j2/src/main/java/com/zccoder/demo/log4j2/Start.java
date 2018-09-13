package com.zccoder.demo.log4j2;

import com.zccoder.demo.log4j2.service.DemoService;
import com.zccoder.demo.log4j2.service.UserService;

/**
 * 标题：启动类<br>
 * 描述：启动类<br>
 * 时间：2018/07/06<br>
 *
 * @author zc
 **/
public class Start {
    public static void main(String[] args) {
        DemoService demoService = new DemoService();
        demoService.run();

        System.out.println("--------------------------------------------");

        UserService userService = new UserService();
        userService.run();

        System.out.println("--------------------------------------------");

        Thread demo = new DemoService();
        demo.setName("DemoServiceThread");
        demo.start();
    }
}
