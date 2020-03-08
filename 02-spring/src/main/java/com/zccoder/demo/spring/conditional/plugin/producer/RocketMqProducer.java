package com.zccoder.demo.spring.conditional.plugin.producer;

import com.zccoder.demo.spring.conditional.plugin.Producer;

/**
 * RocketMq生产者
 *
 * @author zc 2018-06-28
 **/
public class RocketMqProducer implements Producer {

    @Override
    public String hello(String name) {
        String result = "RocketMq say hello to :" + name;
        System.out.println(result);
        return result;
    }
}
