package com.zccoder.demo.redis.lock.service;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Redis分布式锁
 *
 * @author zc 2020-04-23
 */
@Service
public class RedisLockService {

    private static final String STOCK_KEY = "stock";
    private static final String LOCK_KEY = "my-redis-lock";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private Redisson redisson;

    public String stock(int num) {
        // 将库存设置到缓存中
        this.stringRedisTemplate.opsForValue().set(STOCK_KEY, num + "");
        return "库存设置成功：" + num;
    }

    /**
     * 一般实现
     */
    public String normal() {
        String clientId = UUID.randomUUID().toString();
        Boolean result = stringRedisTemplate.opsForValue().setIfAbsent(LOCK_KEY, clientId, 30, TimeUnit.SECONDS);
        if (Objects.isNull(result)) {
            System.out.println("获取Redis锁结果为null");
            return "获取Redis锁结果为null";
        }
        if (!result) {
            System.out.println("系统繁忙，请稍后重试！");
            return "系统繁忙，请稍后重试！";
        }
        try {
            return doService();
        } finally {
            if (clientId.equals(stringRedisTemplate.opsForValue().get(LOCK_KEY))) {
                stringRedisTemplate.delete(LOCK_KEY);
            }
        }
    }

    /**
     * 使用redisson实现
     */
    public String son() {
        // 获取锁对象
        RLock lock = redisson.getLock(LOCK_KEY);
        try {
            // 执行加锁。当锁已被其他线程持有时，当前线程会阻塞；当锁获取成功时，会开启分线程，不断延长锁的超时时间
            lock.lock();
            return doService();
        } finally {
            // 释放锁
            lock.unlock();
        }
    }

    private String doService() {
        String stockString = stringRedisTemplate.opsForValue().get(STOCK_KEY);
        if (Objects.isNull(stockString)) {
            return "未设置库存，请将库存设置到Redis中";
        }

        int stock = Integer.parseInt(stockString);
        if (stock > 0) {
            int realStock = stock - 1;
            stringRedisTemplate.opsForValue().set(STOCK_KEY, realStock + "");
            System.out.println("扣减成功，剩余库存：" + realStock);
            return "扣减成功，剩余库存：" + realStock;
        } else {
            System.out.println("扣减失败，库存不足");
            return "扣减失败，库存不足";
        }
    }
}
