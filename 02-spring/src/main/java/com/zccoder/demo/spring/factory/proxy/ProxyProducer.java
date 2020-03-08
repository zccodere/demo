package com.zccoder.demo.spring.factory.proxy;

/**
 * 代理生产者
 *
 * @author zc 2018-06-28
 **/
public interface ProxyProducer {

    /**
     * 发送消息
     *
     * @param message 消息
     */
    void send(String message);

}
