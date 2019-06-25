package com.zccoder.demo.nacos.api.hello;

import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

/**
 * 降级服务
 *
 * @author zc 2019-06-18
 */
@Component
public class HelloFeignFallbackFactory implements FallbackFactory<HelloFeign> {

    @Override
    public HelloFeign create(Throwable throwable) {
        return string -> {
            throwable.printStackTrace();
            return "system error!" + throwable.getMessage();
        };
    }
}
