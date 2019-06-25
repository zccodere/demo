package com.zccoder.demo.nacos.consumer.fallback;

import com.zccoder.demo.nacos.api.hello.HelloFeign;
import com.zccoder.demo.nacos.api.hello.HelloFeignFallbackFactory;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * 本地自定义降级服务
 * <p>
 * 使用继承Feign默认FallbackFactory类，实现本地自定义降级实现。需要配合 @{@link Primary} 注解实现
 * </p>
 *
 * @author zc 2019-06-18
 */
@Component
@Primary
public class LocalHelloFeignFallbackFactory extends HelloFeignFallbackFactory {

    @Override
    public HelloFeign create(Throwable throwable) {
        return string -> {
            throwable.printStackTrace();
            return "local system error!" + throwable.getMessage();
        };
    }
}
