package com.zccoder.demo.redis.mq.jedis.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Protocol;

/**
 * 创建 Redis 连接
 *
 * @author zc 2018-06-25
 **/
public class JedisFactory {

    private static volatile JedisPool jedisPool;

    public static Jedis getJedis() {
        if (Objects.nonNull(jedisPool)) {
            return jedisPool.getResource();
        }
        URI redisUri;
        try {
            // @符号前面为连接密码，如果未设置密码，删除即可
            redisUri = new URI("redis://zhongguo@127.0.0.1:6379");
        } catch (URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
        GenericObjectPoolConfig redisConfig = new GenericObjectPoolConfig();
        jedisPool = new JedisPool(redisConfig, redisUri.getHost(), redisUri.getPort(), 3000, redisUri.getUserInfo(), Protocol.DEFAULT_DATABASE);
        return jedisPool.getResource();
    }
}
