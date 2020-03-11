package com.zccoder.demo.nacos.consumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 不依赖api包，完全独立
 *
 * @author zc 2019-06-18
 */
@FeignClient(name = "nacos-provider-local", url = "${nacos.provider.url:}", path = "/hello", fallbackFactory = ServiceProviderFeignFallbackFactory.class)
public interface ServiceProviderFeign {

    /**
     * echo
     *
     * @param string 字符
     * @return 处理结果
     */
    @RequestMapping(method = RequestMethod.GET, path = "/echo/{string}")
    String echo(@PathVariable("string") String string);

}
