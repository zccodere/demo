package com.zccoder.demo.spring.conditional.plugin.support.configuration;

import com.zccoder.demo.spring.conditional.plugin.producer.AliyunmqProducer;
import com.zccoder.demo.spring.conditional.plugin.Producer;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;

/**
 * 标题：注册 AliyunMq<br>
 * 描述：注册 AliyunMq<br>
 * 时间：2018/06/28<br>
 *
 * @author zc
 **/
@Configuration
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class AliyunmqConfiguration {

    @Bean(name = "aliyunmqProducer")
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    public Producer scheduledAnnotationProcessor() {
        return new AliyunmqProducer();
    }
}
