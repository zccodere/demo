package com.zccoder.demo.log.log4j;

public class MainStart {
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
