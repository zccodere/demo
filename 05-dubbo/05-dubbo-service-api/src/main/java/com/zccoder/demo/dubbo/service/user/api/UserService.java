package com.zccoder.demo.dubbo.service.user.api;

import com.zccoder.demo.dubbo.service.user.api.bo.ListReqBo;
import com.zccoder.demo.dubbo.service.user.api.bo.ListRspBo;
import com.zccoder.demo.dubbo.service.user.api.bo.SaveAndHelloReqBo;
import com.zccoder.demo.dubbo.service.user.api.bo.SaveAndHelloRspBo;

/**
 * @author zc
 * @version 1.0 2017-09-15
 * @title 用户dubbo服务接口类
 * @describe 用户相关信息
 */
public interface UserService {

    /**
     * 保存用户信息并对用户名说Hello
     *
     * @param saveAndHelloReqBo 入参
     * @return 出参
     */
    SaveAndHelloRspBo saveAndHello(SaveAndHelloReqBo saveAndHelloReqBo);

    /**
     * 获取所有用户
     *
     * @param listReqBo 入参
     * @return 出参
     */
    ListRspBo list(ListReqBo listReqBo);

}
