package com.zccoder.demo.redis.mq.data.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

/**
 * 消息生产者
 *
 * @author zc 2018-06-22
 **/
@Component
public class MessageProducer {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ChannelTopic channelTopic;

    public void publish(String message) {
        String channel = this.channelTopic.getTopic();
        String messageWarp = "时间： " + System.currentTimeMillis() + "," + Thread.currentThread().getName() + "：消息：" + message;
        this.stringRedisTemplate.convertAndSend(channel, messageWarp);
    }
}
