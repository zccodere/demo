package com.zccoder.demo.spring.factory.producer;

import com.zccoder.demo.spring.factory.proxy.ProxyProducer;

/**
 * 标题：RocketMqProducer<br>
 * 描述：RocketMqProducer<br>
 * 时间：2018/06/28<br>
 *
 * @author zc
 **/
public class RocketMqProducer implements ProxyProducer {
    @Override
    public void send(String message) {
        System.out.println("RocketMQ send message：" + message);
    }
}
