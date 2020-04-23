package com.zccoder.demo.redis.rate.service;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Redis分布式限流器
 *
 * @author zc 2020-04-24
 */
@Service
public class RedisRateLimiterService {

    private static final String RATE_LIMITER_PHONE_OVERALL = "rate-limiter-overall:phone:";
    private static final String RATE_LIMITER_PHONE_PER_CLIENT = "rate-limiter-per-client:phone:";
    private static final Logger logger = LoggerFactory.getLogger(RedisRateLimiterService.class);

    @Autowired
    private Redisson redisson;

    public String sendOverall(String phone) {
        if (StringUtils.isBlank(phone)) {
            return "手机号不能为空";
        }

        // 同一个手机号，每5秒钟只允许发送一条短信
        RRateLimiter rateLimiter = redisson.getRateLimiter(RATE_LIMITER_PHONE_OVERALL + phone);

        // 限流类型：OVERALL：全局限流
        rateLimiter.trySetRate(RateType.OVERALL, 1, 5, RateIntervalUnit.SECONDS);

        if (rateLimiter.tryAcquire(1)) {
            logger.info("OVERALL：向手机号码：{} 发送短信", phone);
            return "短信发送成功：" + phone;
        }

        return "请求太频繁，请稍后重试！";
    }

    public String sendPerClient(String phone) {
        if (StringUtils.isBlank(phone)) {
            return "手机号不能为空";
        }

        // 同一个手机号，每5秒钟只允许发送一条短信
        RRateLimiter rateLimiter = redisson.getRateLimiter(RATE_LIMITER_PHONE_PER_CLIENT + phone);
        // 限流类型：PER_CLIENT：每个客户端限流
        rateLimiter.trySetRate(RateType.PER_CLIENT, 1, 5, RateIntervalUnit.SECONDS);

        if (rateLimiter.tryAcquire(1)) {
            logger.info("PER_CLIENT：向手机号码：{} 发送短信", phone);
            return "短信发送成功：" + phone;
        }

        return "请求太频繁，请稍后重试！";
    }

}
