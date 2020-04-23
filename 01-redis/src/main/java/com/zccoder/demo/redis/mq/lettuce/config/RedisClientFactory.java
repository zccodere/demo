package com.zccoder.demo.redis.mq.lettuce.config;

import java.util.Objects;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;

/**
 * Redis 客户端工厂
 * <p>Lettuce详细用法请参考 <a href="https://www.cnblogs.com/throwable/p/11601538.html">Redis高级客户端Lettuce详解</a></p>
 *
 * @author zc 2020-03-08
 */
public class RedisClientFactory {

    private static volatile RedisClient client;

    public static RedisClient getRedisClient() {
        if (Objects.nonNull(client)) {
            return client;
        }
        RedisURI uri = RedisURI.create("redis://127.0.0.1:6379");
        client = RedisClient.create(uri);
        return client;
    }
}
