package com.zccoder.demo.spring.enable.busi;

import com.zccoder.demo.spring.enable.plugin.DemoPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 标题：业务服务使用插件类<br>
 * 描述：业务服务使用插件类<br>
 * 时间：2018/06/28<br>
 *
 * @author zc
 **/
@Service
public class HelloService {

    @Autowired
    private DemoPlugin demoPlugin;

    public void hello(String name) {
        this.demoPlugin.hello(name);
    }
}
