package com.zccoder.demo.spring.factory.producer;

import com.zccoder.demo.spring.factory.proxy.ProxyProducer;

/**
 * 标题：RedisMqProducer<br>
 * 描述：RedisMqProducer<br>
 * 时间：2018/06/28<br>
 *
 * @author zc
 **/
public class RedisMqProducer implements ProxyProducer {

    @Override
    public void send(String message) {
        System.out.println("RedisMQ send message：" + message);
    }
}
