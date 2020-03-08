package com.zccoder.demo.spring.factory.producer;

import com.zccoder.demo.spring.factory.proxy.ProxyProducer;

/**
 * 默认生产者
 *
 * @author zc 2018-06-28
 **/
public class DefaultProxyProducer implements ProxyProducer {

    @Override
    public void send(String message) {
        System.out.println("插件未启用，不发送消息");
    }
}
