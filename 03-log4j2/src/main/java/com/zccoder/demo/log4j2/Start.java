package com.zccoder.demo.log4j2;

import com.zccoder.demo.log4j2.service.DemoService;
import com.zccoder.demo.log4j2.service.UserService;

/**
 * 启动类
 *
 * @author zc 2018-07-06
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
