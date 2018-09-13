package com.zccoder.demo.redis.mq.jedis.consumer;

import redis.clients.jedis.JedisPubSub;

/**
 * 标题：自定义消息消费者<br>
 * 描述：自定义消息消费者<br>
 * 时间：2018/06/25<br>
 *
 * @author zc
 **/
public class MessageConsumer extends JedisPubSub {
    @Override
    public void onMessage(String channel, String message) {
        // 监听到频道发送过来的信息
        System.out.println("接收到频道：" + channel + "；发送的消息：" + message);
    }
}
