package com.zccoder.demo.nacos.provider.controller;

import com.zccoder.demo.nacos.api.hello.HelloFeign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * 服务实现
 *
 * @author zc 2019-06-18
 */
@RestController
@RequestMapping("/hello")
public class HelloController implements HelloFeign {

    @Override
    @GetMapping("/echo/{string}")
    public String echo(@PathVariable("string") String string) {
        Random random = new Random();
        // 随机模拟服务访问超时
        if (random.nextBoolean()) {
            try {
                System.out.println("接收到请求，开始睡眠");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "Hello Nacos Discovery " + string;
    }
}
