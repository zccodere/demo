package com.zccoder.demo.spring.schema.apply.service.impl;

import com.zccoder.demo.spring.schema.apply.service.HelloService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 标题：Hello 服务实现<br>
 * 描述：Hello 服务实现<br>
 * 时间：2018/10/16<br>
 *
 * @author zc
 **/
@Service("helloService")
public class HelloServiceImpl implements HelloService {

    @ResponseBody
    @Override
    public String hello(String name) {
        return "Hello " + name;
    }
}
