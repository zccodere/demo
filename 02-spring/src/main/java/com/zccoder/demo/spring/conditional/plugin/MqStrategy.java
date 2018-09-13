package com.zccoder.demo.spring.conditional.plugin;

/**
 * 标题：消息策略<br>
 * 描述：消息策略<br>
 * 时间：2018/06/28<br>
 *
 * @author zc
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

    public static boolean isRocketMQ(String strategy) {
        return RocketMQ.name().equals(strategy);
    }

    public static boolean isAliyunMQ(String strategy) {
        return AliyunMQ.name().equals(strategy);
    }

    public static boolean isRedisMQ(String strategy) {
        return RedisMQ.name().equals(strategy);
    }
}
