package com.zccoder.demo.spring.factory.producer;

import com.zccoder.demo.spring.factory.proxy.ProxyProducer;

/**
 * RedisMqProducer
 *
 * @author zc 2018-06-28
 **/
public class RedisMqProducer implements ProxyProducer {

    @Override
    public void send(String message) {
        System.out.println("RedisMQ send message：" + message);
    }
}
