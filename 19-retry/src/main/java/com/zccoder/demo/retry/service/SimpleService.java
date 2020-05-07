package com.zccoder.demo.retry.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.remoting.RemoteAccessException;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SimpleService {

    private static final Logger log = LoggerFactory.getLogger(SimpleService.class);

    @Retryable(value = RemoteAccessException.class, maxAttempts = 1)
    public String service(String userName) {
        log.info("service：{}", userName);

        if (new Random().nextBoolean()) {
            log.info("随机失败了");
            throw new OutOfMemoryError("随机失败");
        }

        return "service：" + userName;
    }

    @Recover
    public String recover(RemoteAccessException e, String userName) {
        log.info("失败次数已达1次，执行 recover 方法：" + userName);
        return "recover："+userName;
    }

    @Recover
    public String recover1(Exception e, String userName) {
        log.info("失败次数已达1次，执行 recover 方法：" + userName);
        return "recover1："+userName;
    }

    @Recover
    public String recover2(Throwable e, String userName) {
        log.info("失败次数已达1次，执行 recover 方法：" + userName);
        return "recover2："+userName;
    }
}
