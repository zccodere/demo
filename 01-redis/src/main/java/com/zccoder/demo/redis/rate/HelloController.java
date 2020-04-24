package com.zccoder.demo.redis.rate;

import com.zccoder.demo.redis.rate.service.RedisRateLimiterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制器
 *
 * @author zc 2020-04-24
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private RedisRateLimiterService service;

    @GetMapping("/send/overall/{phone}")
    public String sendOverall(@PathVariable("phone") String phone) {
        return this.service.sendOverall(phone);
    }

    @GetMapping("/send/client/{phone}")
    public String sendPerClient(@PathVariable("phone") String phone) {
        return this.service.sendPerClient(phone);
    }

}
