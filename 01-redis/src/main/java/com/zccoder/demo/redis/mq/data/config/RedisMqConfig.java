package com.zccoder.demo.redis.mq.data.config;

import com.zccoder.demo.redis.mq.data.consumer.MessageConsumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * Redis Mq 配置类
 *
 * @author zc 2018-06-22
 **/
@Configuration
public class RedisMqConfig {

    @Bean
    public MessageListenerAdapter messageListenerAdapter(MessageConsumer messageConsumer) {
        return new MessageListenerAdapter(messageConsumer);
    }

    @Bean
    ChannelTopic channelTopic() {
        return new ChannelTopic("pub:sub:queue");
    }

    @Bean
    RedisMessageListenerContainer redisContainer(LettuceConnectionFactory redisConnectionFactory, MessageListenerAdapter messageListenerAdapter) {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        container.addMessageListener(messageListenerAdapter, channelTopic());
        return container;
    }

}
