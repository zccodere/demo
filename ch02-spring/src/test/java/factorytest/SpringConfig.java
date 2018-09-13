package factorytest;

import com.zccoder.demo.redis.mq.factory.producer.AliyunMqProducer;
import com.zccoder.demo.redis.mq.factory.proxy.ProxyProducerConfig;
import com.zccoder.demo.redis.mq.factory.proxy.ProxyProducerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("com.zccoder.demo.mq.redis.factorytest")
@Configuration
public class SpringConfig {

    @Bean
    public ProxyProducerConfig proxyProducerConfig(){
        ProxyProducerConfig config = new ProxyProducerConfig();
        config.setStrategy(AliyunMqProducer.class);
        config.setEnable(true);
        return config;
    }

    @Bean("userCreateProducer")
    public ProxyProducerFactoryBean userCreateProducer(){
        return new ProxyProducerFactoryBean();
    }
}
