package com.zccoder.demo.spring.conditional.plugin.producer;

public class RedismqProducer implements Producer {
    @Override
    public String hello(String name) {
        String result = "Redismq say hello to :" + name;
        System.out.println(result);
        return result;
    }
}
