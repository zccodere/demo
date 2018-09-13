package com.zccoder.demo.spring.conditional.plugin.producer;

public class RocketmqProducer implements Producer {
    @Override
    public String hello(String name) {
        String result = "Rocketmq say hello to :" + name;
        System.out.println(result);
        return result;
    }
}
