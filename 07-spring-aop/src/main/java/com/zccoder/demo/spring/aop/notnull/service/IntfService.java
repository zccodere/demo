package com.zccoder.demo.spring.aop.notnull.service;

import com.zccoder.demo.spring.aop.notnull.annotation.ParameterNotNull;
import com.zccoder.demo.spring.aop.notnull.aspect.RespMessage;
import org.springframework.stereotype.Service;

/**
 * 一个 Service
 *
 * @author ZhangCheng
 * @version V1.0
 * @date 2017-03-20
 */
@Service(value="IntfService")
public class IntfService {
    
    @ParameterNotNull(value={"uname","upaswd"})
    public RespMessage getUserInfo(String uid, String uname, String upaswd, String email){
        RespMessage respMessage = new RespMessage();
        System.out.println(uid+uname+upaswd+email);
        respMessage.setCode("200");
        respMessage.setMsg("成功");
        return respMessage;
    }

}
