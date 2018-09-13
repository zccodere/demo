package com.zccoder.demo.redis.mq.data.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

/**
 * 标题：消息生产者<br>
 * 描述：消息生产者<br>
 * 时间：2018/06/22<br>
 *
 * @author zc
 **/
@Component
public class MessageProducer {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private ChannelTopic channelTopic;

    public void publish(String message) {
        this.stringRedisTemplate.convertAndSend(this.channelTopic.getTopic(), "时间： " + System.currentTimeMillis() + "," + Thread.currentThread().getName() + "：消息：" + message);
    }
}
