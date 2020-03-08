package com.zccoder.demo.spring.enable.service;

import com.zccoder.demo.spring.enable.plugin.DemoPlugin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 业务服务使用插件类
 *
 * @author zc 2018-06-28
 **/
@Service
public class HelloService {

    @Autowired
    private DemoPlugin demoPlugin;

    public void hello(String name) {
        this.demoPlugin.hello(name);
    }
}
