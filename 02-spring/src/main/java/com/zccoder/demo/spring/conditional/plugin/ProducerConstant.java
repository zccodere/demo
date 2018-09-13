package com.zccoder.demo.spring.conditional.plugin;

import com.zccoder.demo.spring.conditional.plugin.producer.AliyunmqProducer;
import com.zccoder.demo.spring.conditional.plugin.producer.RedismqProducer;
import com.zccoder.demo.spring.conditional.plugin.producer.RocketmqProducer;

/**
 * 标题：常量<br>
 * 描述：常量<br>
 * 时间：2018/06/28<br>
 *
 * @author zc
 **/
public class ProducerConstant {

    public enum Supportmq{
        /**
         * AliyunMQ
         */
        AliyunMQ("aliyunmqProducer",AliyunmqProducer.class),
        /**
         * RocketMQ
         */
        RocketMQ("rocketmqProducer",RocketmqProducer.class),
        /**
         * RedisMQ
         */
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
