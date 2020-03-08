package com.zccoder.demo.spring.factory.config;

import com.zccoder.demo.spring.factory.producer.AliyunMqProducer;
import com.zccoder.demo.spring.factory.producer.RedisMqProducer;
import com.zccoder.demo.spring.factory.producer.RocketMqProducer;
import com.zccoder.demo.spring.factory.proxy.ProxyProducerConfig;
import com.zccoder.demo.spring.factory.proxy.ProxyProducerFactoryBean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 配置
 *
 * @author zc 2018-06-28
 **/
@ComponentScan("com.zccoder.demo.spring.factory")
@Configuration
public class SpringConfig {

    @Bean
    public ProxyProducerConfig proxyProducerConfig() {
        ProxyProducerConfig config = new ProxyProducerConfig();
        config.setStrategy(AliyunMqProducer.class);
        config.setEnable(true);
        return config;
    }

    @Bean("userCreateProducer")
    public ProxyProducerFactoryBean userCreateProducer() {
        return new ProxyProducerFactoryBean();
    }

    @Bean("userDeleteProducer")
    public ProxyProducerFactoryBean userDeleteProducer() {
        ProxyProducerFactoryBean producer = new ProxyProducerFactoryBean();
        ProxyProducerConfig config = new ProxyProducerConfig();
        config.setStrategy(RedisMqProducer.class);
        producer.setProxyProducerStrategy(config);
        return producer;
    }

    @Bean("userQueryProducer")
    public ProxyProducerFactoryBean userQueryProducer() {
        ProxyProducerFactoryBean producer = new ProxyProducerFactoryBean();
        ProxyProducerConfig config = new ProxyProducerConfig();
        config.setStrategy(RocketMqProducer.class);
        producer.setProxyProducerStrategy(config);
        return producer;
    }
}
