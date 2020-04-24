package com.zccoder.demo.spring.aop.mark;

import org.springframework.stereotype.Component;

/**
 * 自定义服务
 *
 * @author zc 2020-04-24
 */
@Component
public class MyService {

    @Mark
    public void doService() {
        // 该方法会被拦截
        System.out.println("ss");
    }
}
