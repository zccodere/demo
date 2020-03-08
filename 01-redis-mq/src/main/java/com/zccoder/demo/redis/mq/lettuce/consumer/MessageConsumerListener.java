package com.zccoder.demo.redis.mq.lettuce.consumer;

import io.lettuce.core.pubsub.RedisPubSubListener;

/**
 * 消息消费者
 *
 * @author zc 2020-03-08
 */
public class MessageConsumerListener implements RedisPubSubListener<String, String> {

    @Override
    public void message(String channel, String message) {
        System.out.println("message：" + channel + "；" + message);
    }

    @Override
    public void message(String pattern, String channel, String message) {
        System.out.println("message：" + pattern + "；" + channel + "；" + message);
    }

    @Override
    public void subscribed(String channel, long count) {
        System.out.println("subscribed：" + channel + "；" + count);
    }

    @Override
    public void psubscribed(String pattern, long count) {
        System.out.println("p subscribed：" + pattern + "；" + count);
    }

    @Override
    public void unsubscribed(String channel, long count) {
        System.out.println("un subscribed：" + channel + "；" + count);
    }

    @Override
    public void punsubscribed(String pattern, long count) {
        System.out.println("p un subscribed：" + pattern + "；" + count);
    }
}
