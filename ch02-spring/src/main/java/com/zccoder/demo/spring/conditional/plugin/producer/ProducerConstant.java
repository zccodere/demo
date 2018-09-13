package com.zccoder.demo.spring.conditional.plugin.producer;

public class ProducerConstant {

    public enum Supportmq{

        AliyunMQ("aliyunmqProducer",AliyunmqProducer.class),

        RocketMQ("rocketmqProducer",RocketmqProducer.class),
        
        RedisMQ("redismqProducer",RedismqProducer.class);

        private String beanName;

        private Class beanClass;

        Supportmq(String beanName,Class beanClass){
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
