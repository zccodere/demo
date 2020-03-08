package com.zccoder.demo.redis.mq.jedis.producer;

import com.zccoder.demo.redis.mq.jedis.TopicConstant;
import com.zccoder.demo.redis.mq.jedis.config.JedisFactory;

import java.util.concurrent.TimeUnit;

import redis.clients.jedis.Jedis;

/**
 * 消息发送者
 *
 * @author zc 2018-06-25
 **/
public class MessageProducerStart {

    public static void main(String[] args) throws Exception {
        Jedis jedis = JedisFactory.getJedis();

        System.out.println("开始发送消息：");
        int total = 10;
        for (int i = 0; i < total; i++) {
            String message = "我是消息" + i;
            jedis.publish(TopicConstant.TOPIC_TEST_ONE, message);
            System.out.println("发送消息：" + message);
            // 休眠1秒后再发送
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("结束发送消息");
    }
}
