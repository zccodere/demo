package com.zccoder.demo.spring.factory.producer;


import com.zccoder.demo.spring.factory.proxy.ProxyProducer;

/**
 * 标题：默认生产者<br>
 * 描述：默认生产者<br>
 * 时间：2018/06/28<br>
 *
 * @author zc
 **/
public class DefaultProxyProducer implements ProxyProducer {
    
    @Override
    public void send(String message) {
        System.out.println("插件未启用，不发送消息");
    }
}
