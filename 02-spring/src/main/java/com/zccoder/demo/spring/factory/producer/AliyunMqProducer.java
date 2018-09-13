package com.zccoder.demo.spring.factory.producer;

import com.zccoder.demo.spring.factory.proxy.ProxyProducer;

/**
 * 标题：AliyunMqProducer<br>
 * 描述：AliyunMqProducer<br>
 * 时间：2018/06/28<br>
 *
 * @author zc
 **/
public class AliyunMqProducer implements ProxyProducer {
    @Override
    public void send(String message) {
        System.out.println("AliyunMQ send message：" + message);
    }
}
