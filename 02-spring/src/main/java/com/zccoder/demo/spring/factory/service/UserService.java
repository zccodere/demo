package com.zccoder.demo.spring.factory.service;

import com.zccoder.demo.spring.factory.proxy.ProxyProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 用户服务
 *
 * @author zc 2018-06-28
 **/
@Service
public class UserService {

    @Autowired
    @Qualifier("userCreateProducer")
    private ProxyProducer userCreateProducer;

    @Autowired
    @Qualifier("userDeleteProducer")
    private ProxyProducer userDeleteProducer;

    @Autowired
    @Qualifier("userQueryProducer")
    private ProxyProducer userQueryProducer;

    public void create(String name) {
        System.out.println("创建用户：" + name);
        userCreateProducer.send(name);
    }

    public void delete(String name) {
        System.out.println("删除用户：" + name);
        userDeleteProducer.send(name);
    }

    public void query(String name) {
        System.out.println("查询用户：" + name);
        userQueryProducer.send(name);
    }

}
