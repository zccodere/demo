package com.zccoder.demo.dubbo.rest.rest;

import com.zccoder.demo.dubbo.service.user.api.UserService;
import com.zccoder.demo.dubbo.service.user.api.bo.ListReqBo;
import com.zccoder.demo.dubbo.service.user.api.bo.ListRspBo;
import com.zccoder.demo.dubbo.service.user.api.bo.SaveAndHelloReqBo;
import com.zccoder.demo.dubbo.service.user.api.bo.SaveAndHelloRspBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zc
 * @version 1.0 2017-09-15
 * @title REST
 * @describe REST
 */
@RestController
public class UserRest {

    @Autowired
    private UserService userService;

    @GetMapping("/save")
    public Object save(String name, String password) {
        SaveAndHelloReqBo reqBo = new SaveAndHelloReqBo();
        reqBo.setName(name);
        reqBo.setPassword(password);
        SaveAndHelloRspBo rsqBo = userService.saveAndHello(reqBo);
        return rsqBo;
    }

    @GetMapping("/list")
    public Object list(String name, String password) {
        ListReqBo reqBo = new ListReqBo();
        reqBo.setName(name);
        reqBo.setPassword(password);
        ListRspBo rsqBo = userService.list(reqBo);
        return rsqBo;
    }
}
