package com.zccoder.demo.nacos.api.hello;

import com.zccoder.demo.nacos.api.HelloApiConst;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 定义服务接口
 * <p>
 * 如果已配置url的值，Feign会走普通请求方式；<br/> 如果未配置url的值，Feign会走服务发现方式。
 * </p>
 *
 * @author zc 2019-06-18
 */
@FeignClient(name = HelloApiConst.SERVICE_NAME, url = HelloApiConst.SERVICE_URL, path = "/hello", fallbackFactory = HelloFeignFallbackFactory.class)
public interface HelloFeign {

    /**
     * echo
     *
     * @param string 字符
     * @return 处理结果
     */
    @RequestMapping(method = RequestMethod.GET, path = "/echo/{string}")
    String echo(@PathVariable("string") String string);
}
