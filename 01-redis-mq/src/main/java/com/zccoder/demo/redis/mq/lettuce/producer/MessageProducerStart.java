package com.zccoder.demo.redis.mq.lettuce.producer;

import com.zccoder.demo.redis.mq.lettuce.LettuceTopicConstant;
import com.zccoder.demo.redis.mq.lettuce.config.RedisClientFactory;

import java.util.concurrent.TimeUnit;

import io.lettuce.core.RedisClient;
import io.lettuce.core.pubsub.api.sync.RedisPubSubCommands;

/**
 * 消息发送者
 *
 * @author zc 2020-03-08
 */
public class MessageProducerStart {

    public static void main(String[] args) throws Exception {
        RedisClient redisClient = RedisClientFactory.getRedisClient();
        RedisPubSubCommands<String, String> commands = redisClient.connectPubSub().sync();

        System.out.println("开始发送消息：");
        int total = 10;
        for (int i = 0; i < total; i++) {
            String message = "我是消息" + i;
            commands.publish(LettuceTopicConstant.TOPIC_TEST_ONE, message);
            System.out.println("发送消息：" + message);
            // 休眠1秒后再发送
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("结束发送消息");
    }
}
