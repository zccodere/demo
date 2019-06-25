package com.zccoder.demo.nacos.consumer.controller;

import com.zccoder.demo.nacos.api.hello.HelloFeign;
import com.zccoder.demo.nacos.consumer.feign.ServiceProviderFeign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制层
 *
 * @author zc 2019-06-18
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private HelloFeign helloFeign;
    @Autowired
    private ServiceProviderFeign serviceProviderFeign;

    @RequestMapping(method = RequestMethod.GET, value = "/echo/{string}")
    public String echo(@PathVariable("string") String string) {
        return helloFeign.echo(string);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/echo/service/{string}")
    public String echoService(@PathVariable("string") String string) {
        return serviceProviderFeign.echo(string);
    }
}
