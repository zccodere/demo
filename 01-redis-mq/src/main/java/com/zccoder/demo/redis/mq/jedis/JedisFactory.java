package com.zccoder.demo.redis.mq.jedis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Protocol;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * 标题：创建连接<br>
 * 描述：创建连接<br>
 * 时间：2018/06/25<br>
 *
 * @author zc
 **/
public class JedisFactory {

    private static JedisPool jedisPool;

    public static Jedis getJedis() {
        if (jedisPool != null) {
            return jedisPool.getResource();
        }
        URI redisUri = null;
        try {
            redisUri = new URI("redis://zhongguo@127.0.0.1:6379");
        } catch (URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
        GenericObjectPoolConfig redisConfig = new GenericObjectPoolConfig();
        jedisPool = new JedisPool(redisConfig, redisUri.getHost(), redisUri.getPort(), 3000, redisUri.getUserInfo(), Protocol.DEFAULT_DATABASE);
        return jedisPool.getResource();
    }
}
