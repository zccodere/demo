package com.zccoder.demo.spring.schema.apply.service.impl;

import com.zccoder.demo.spring.schema.apply.service.HelloService;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Hello 服务实现
 *
 * @author zc 2018-10-16
 **/
@Service("helloService")
public class HelloServiceImpl implements HelloService {

    @ResponseBody
    @Override
    public String hello(String name) {
        return "{\"Hello\":\"" + name + "\"}";
    }
}
