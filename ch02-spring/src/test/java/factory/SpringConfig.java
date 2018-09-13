package factory;

import com.zccoder.demo.redis.mq.factory.producer.AliyunMqProducer;
import com.zccoder.demo.redis.mq.factory.producer.RedisMqProducer;
import com.zccoder.demo.redis.mq.factory.producer.RocketMqProducer;
import com.zccoder.demo.redis.mq.factory.proxy.ProxyProducerConfig;
import com.zccoder.demo.redis.mq.factory.proxy.ProxyProducerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("com.zccoder.demo.mq.redis.factory")
@Configuration
public class SpringConfig {

    @Bean
    public ProxyProducerConfig proxyProducerConfig(){
        ProxyProducerConfig config = new ProxyProducerConfig();
        config.setStrategy(AliyunMqProducer.class);
        return config;
    }

    @Bean("userCreateProducer")
    public ProxyProducerFactoryBean userCreateProducer(){
        return new ProxyProducerFactoryBean();
    }

    @Bean("userDeleteProducer")
    public ProxyProducerFactoryBean userDeleteProducer(){
        ProxyProducerFactoryBean producer = new ProxyProducerFactoryBean();
        ProxyProducerConfig config = new ProxyProducerConfig();
        config.setStrategy(RedisMqProducer.class);
        producer.setProxyProducerStrategy(config);
        return producer;
    }

    @Bean("userQueryProducer")
    public ProxyProducerFactoryBean userQueryProducer(){
        ProxyProducerFactoryBean producer = new ProxyProducerFactoryBean();
        ProxyProducerConfig config = new ProxyProducerConfig();
        config.setStrategy(RocketMqProducer.class);
        producer.setProxyProducerStrategy(config);
        return producer;
    }
}
