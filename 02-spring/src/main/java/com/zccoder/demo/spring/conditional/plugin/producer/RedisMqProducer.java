package com.zccoder.demo.spring.conditional.plugin.producer;

import com.zccoder.demo.spring.conditional.plugin.Producer;

/**
 * RedisMq生产者
 *
 * @author zc 2018-06-28
 **/
public class RedisMqProducer implements Producer {

    @Override
    public String hello(String name) {
        String result = "RedisMq say hello to :" + name;
        System.out.println(result);
        return result;
    }
}
