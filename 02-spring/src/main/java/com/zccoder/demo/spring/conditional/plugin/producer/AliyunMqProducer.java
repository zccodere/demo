package com.zccoder.demo.spring.conditional.plugin.producer;

import com.zccoder.demo.spring.conditional.plugin.Producer;

/**
 * AliyunMq生产者
 *
 * @author zc 2018-06-28
 **/
public class AliyunMqProducer implements Producer {

    @Override
    public String hello(String name) {
        String result = "AliyunMq say hello to :" + name;
        System.out.println(result);
        return result;
    }
}
