package com.zccoder.demo.redis.mq.lettuce.consumer;

import com.zccoder.demo.redis.mq.lettuce.LettuceTopicConstant;
import com.zccoder.demo.redis.mq.lettuce.config.RedisClientFactory;

import java.util.concurrent.TimeUnit;

import io.lettuce.core.RedisClient;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;

/**
 * 消息消费者
 *
 * @author zc 2020-03-08
 */
public class MessageConsumerStart {

    public static void main(String[] args) throws InterruptedException {
        RedisClient redisClient = RedisClientFactory.getRedisClient();
        StatefulRedisPubSubConnection<String, String> connection = redisClient.connectPubSub();
        connection.addListener(new MessageConsumerListener());
        System.out.println("消息消费者开始接收消息");

        connection.sync().subscribe(LettuceTopicConstant.TOPIC_TEST_ONE);

        TimeUnit.MINUTES.sleep(1);
    }
}
