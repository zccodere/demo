package com.zccoder.demo.redis.mq.jedis.consumer;

import com.zccoder.demo.redis.mq.jedis.TopicConstant;
import com.zccoder.demo.redis.mq.jedis.config.JedisFactory;

import redis.clients.jedis.Jedis;

/**
 * 消息消费者
 *
 * @author zc 2018-06-25
 **/
public class MessageConsumerStart {

    public static void main(String[] args) {
        Jedis jedis = JedisFactory.getJedis();
        jedis.subscribe(new MessageConsumer(), TopicConstant.TOPIC_TEST_ONE);

        System.out.println("消息消费者开始接收消息");
    }
}
