package com.zccoder.demo.spring.factory.producer;

import com.zccoder.demo.spring.factory.proxy.ProxyProducer;

/**
 * AliyunMqProducer
 *
 * @author zc 2018-06-28
 **/
public class AliyunMqProducer implements ProxyProducer {

    @Override
    public void send(String message) {
        System.out.println("AliyunMQ send message：" + message);
    }
}
