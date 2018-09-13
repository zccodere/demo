package com.zccoder.demo.redis.mq.jedis;

import com.zccoder.demo.redis.mq.jedis.consumer.MessageConsumer;
import redis.clients.jedis.Jedis;

/**
 * 标题：消息消费者<br>
 * 描述：消息消费者<br>
 * 时间：2018/06/25<br>
 *
 * @author zc
 **/
public class MessageConsumerStart {

    public static void main(String[] args) throws Exception {
        Jedis jedis = JedisFactory.getJedis();

        jedis.subscribe(new MessageConsumer(), TopicConstant.TOPIC_TEST_ONE);

        System.out.println("消息消费者开始接收消息");
    }
}
