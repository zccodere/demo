package com.zccoder.demo.dubbo.consumer.rest;

import com.zccoder.demo.dubbo.api.user.UserService;
import com.zccoder.demo.dubbo.api.user.bo.ListReqBo;
import com.zccoder.demo.dubbo.api.user.bo.SaveAndHelloReqBo;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制层
 *
 * @author zc 2017-09-15
 */
@RestController
public class UserRest {

    @Reference
    private UserService userService;

    @GetMapping("/save")
    public Object save(String name, String password) {
        SaveAndHelloReqBo reqBo = new SaveAndHelloReqBo();
        reqBo.setName(name);
        reqBo.setPassword(password);
        return userService.saveAndHello(reqBo);
    }

    @GetMapping("/list")
    public Object list(String name, String password) {
        ListReqBo reqBo = new ListReqBo();
        reqBo.setName(name);
        reqBo.setPassword(password);
        return userService.list(reqBo);
    }
}
