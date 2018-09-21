package com.zccoder.demo.spring.aop.log.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.zccoder.demo.spring.aop.log.aop.Action;
import com.zccoder.demo.spring.aop.log.dto.ReturnMessage;
import com.zccoder.demo.spring.aop.log.service.UserService;
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
    @Action(name = "注解式拦截的add操作")
    public ReturnMessage getUserInfoByName(String jsessionId, String userName) throws Exception {
        ReturnMessage returnMessage = new ReturnMessage();

        Map<String, Object> args = new HashMap<>(16);
        args.put("jsessionId", jsessionId);
        args.put("userName", userName);

        returnMessage.setCode("200");
        returnMessage.setMsg("获取成功");
        returnMessage.setArgs(args);

//        boolean temp = true;
//        if(temp == true){
//            throw new IllegalArgumentException();
//        }

        return returnMessage;
    }

    @Override
    @Action(name = "注解式拦截的add操作")
    public ReturnMessage getUserInfoById(String jsessionId, String userId) {
        ReturnMessage returnMessage = new ReturnMessage();
        Map<String, Object> args = new HashMap<>(16);
        args.put("jsessionId", jsessionId);
        args.put("userId", userId);

        returnMessage.setCode("200");
        returnMessage.setMsg("获取成功");
        returnMessage.setArgs(args);

        return returnMessage;
    }
}
