package com.zccoder.demo.spring.factory.producer;

import com.zccoder.demo.spring.factory.proxy.ProxyProducer;

/**
 * RocketMqProducer
 *
 * @author zc 2018-06-28
 **/
public class RocketMqProducer implements ProxyProducer {

    @Override
    public void send(String message) {
        System.out.println("RocketMQ send message：" + message);
    }
}
