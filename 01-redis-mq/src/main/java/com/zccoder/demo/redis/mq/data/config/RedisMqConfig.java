package com.zccoder.demo.redis.mq.data.config;

import com.zccoder.demo.redis.mq.data.consumer.MessageConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * 标题：RedisMq配置类<br>
 * 描述：RedisMq配置类<br>
 * 时间：2018/06/22<br>
 *
 * @author zc
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
    RedisMessageListenerContainer redisContainer(JedisConnectionFactory jedisConnectionFactory, MessageListenerAdapter messageListenerAdapter) {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory);
        container.addMessageListener(messageListenerAdapter, channelTopic());
        return container;
    }

}
