package com.zccoder.demo.spring.aop.notnull.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.zccoder.demo.spring.aop.notnull.annotation.LoginRequired;
import com.zccoder.demo.spring.aop.notnull.annotation.ParameterNotNull;
import com.zccoder.demo.spring.aop.notnull.aspect.RespMessage;
import com.zccoder.demo.spring.aop.notnull.service.UserService;
import org.springframework.stereotype.Service;


/**
 * 用户 Service Impl
 *
 * @author ZhangCheng
 * @version V1.0
 * @date 2017-03-20
 */
@Service(value = "UserService")
public class UserServiceImpl implements UserService {

    @Override
    @LoginRequired
    @ParameterNotNull()
    public RespMessage getUserInfoByName(String jsessionId, String userName) throws Exception {
        RespMessage returnMessage = new RespMessage();

        Map<String, Object> args = new HashMap<>(16);
        args.put("jsessionId", jsessionId);
        args.put("userName", userName);

        returnMessage.setCode("200");
        returnMessage.setMsg("获取成功");
        returnMessage.setArgs(args);

        return returnMessage;
    }

    @Override
    @LoginRequired
    @ParameterNotNull(value = {"userId"})
    public RespMessage getUserInfoById(String jsessionId, String userId) {
        RespMessage returnMessage = new RespMessage();
        Map<String, Object> args = new HashMap<>(16);
        args.put("jsessionId", jsessionId);
        args.put("userId", userId);

        returnMessage.setCode("200");
        returnMessage.setMsg("获取成功");
        returnMessage.setArgs(args);

        return returnMessage;
    }

}
