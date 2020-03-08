package com.zccoder.demo.spring.conditional.plugin;

/**
 * 消息策略枚举
 *
 * @author zc 2018-06-28
 **/
public enum MqStrategy {

    /**
     * RocketMQ
     */
    RocketMQ,
    /**
     * AliyunMQ
     */
    AliyunMQ,
    /**
     * RedisMQ
     */
    RedisMQ;

    public static boolean isRocketMq(String strategy) {
        return RocketMQ.name().equals(strategy);
    }

    public static boolean isAliyunMq(String strategy) {
        return AliyunMQ.name().equals(strategy);
    }

    public static boolean isRedisMq(String strategy) {
        return RedisMQ.name().equals(strategy);
    }
}
