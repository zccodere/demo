package com.zccoder.demo.redis.mq.jedis;

import redis.clients.jedis.Jedis;

/**
 * 标题：消息发送者<br>
 * 描述：消息发送者<br>
 * 时间：2018/06/25<br>
 *
 * @author zc
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
            Thread.sleep(1000L);
        }
        System.out.println("结束发送消息");
    }
}
