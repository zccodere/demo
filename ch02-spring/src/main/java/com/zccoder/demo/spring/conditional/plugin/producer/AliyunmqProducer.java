package com.zccoder.demo.spring.conditional.plugin.producer;

public class AliyunmqProducer implements Producer {
    @Override
    public String hello(String name) {
        String result = "Aliyunmq say hello to :" + name;
        System.out.println(result);
        return result;
    }
}
