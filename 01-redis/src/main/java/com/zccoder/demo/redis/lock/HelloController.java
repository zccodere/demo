package com.zccoder.demo.redis.lock;

import com.zccoder.demo.redis.lock.service.RedisLockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 *
 * @author zc 2020-04-23
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private RedisLockService redisLockService;

    @GetMapping("/stock/{num}")
    public String stock(@PathVariable("num") int num) {
        return this.redisLockService.stock(num);
    }

    @GetMapping("/normal")
    public String normal() {
        return this.redisLockService.normal();
    }

    @GetMapping("/son")
    public String son() {
        return this.redisLockService.son();
    }
}