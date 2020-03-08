package com.zccoder.demo.spring.conditional.plugin;

import com.zccoder.demo.spring.conditional.plugin.producer.AliyunMqProducer;
import com.zccoder.demo.spring.conditional.plugin.producer.RedisMqProducer;
import com.zccoder.demo.spring.conditional.plugin.producer.RocketMqProducer;

/**
 * 常量
 *
 * @author zc 2018-06-28
 **/
public class ProducerConstant {

    public enum SupportMq {
        /**
         * AliyunMQ
         */
        AliyunMQ("aliyunMqProducer", AliyunMqProducer.class),
        /**
         * RocketMQ
         */
        RocketMQ("rocketMqProducer", RocketMqProducer.class),
        /**
         * RedisMQ
         */
        RedisMQ("redisMqProducer", RedisMqProducer.class);

        private String beanName;

        private Class beanClass;

        SupportMq(String beanName, Class beanClass) {
            this.beanName = beanName;
            this.beanClass = beanClass;
        }

        public String getBeanName() {
            return beanName;
        }

        public Class getBeanClass() {
            return beanClass;
        }
    }

}
