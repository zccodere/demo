package com.zccoder.demo.redis.mq.data.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * 标题：消息消费者<br>
 * 描述：消息消费者<br>
 * 时间：2018/06/22<br>
 *
 * @author zc
 **/
@Component
public class MessageConsumer implements MessageListener {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onMessage(Message message, byte[] channel) {
        logger.info("接收到消息：body={}", new String(message.getBody()));
        logger.info("接收到消息：channel={}", new String(message.getChannel()));
        logger.info("接收到消息：channel={}", new String(channel));
    }
}
