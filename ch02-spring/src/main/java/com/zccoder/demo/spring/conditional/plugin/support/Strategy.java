package com.zccoder.demo.spring.conditional.plugin.support;

public enum Strategy {
    RocketMQ, AliyunMQ, RedisMQ;

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
