package com.zccoder.demo.nacos.consumer.feign;

import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

/**
 * 降级服务
 *
 * @author zc 2019-06-18
 */
@Component
public class ServiceProviderFeignFallbackFactory implements FallbackFactory<ServiceProviderFeign> {
    @Override
    public ServiceProviderFeign create(Throwable throwable) {
        return string -> {
            throwable.printStackTrace();
            return "system error!" + throwable.getMessage();
        };
    }
}
