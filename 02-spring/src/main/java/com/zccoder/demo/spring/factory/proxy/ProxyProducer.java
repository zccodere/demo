package com.zccoder.demo.spring.factory.proxy;

/**
 * 标题：代理生产者<br>
 * 描述：代理生产者<br>
 * 时间：2018/06/28<br>
 *
 * @author zc
 **/
public interface ProxyProducer {

    /**
     * 发送消息
     *
     * @param message 消息
     */
    void send(String message);

}
