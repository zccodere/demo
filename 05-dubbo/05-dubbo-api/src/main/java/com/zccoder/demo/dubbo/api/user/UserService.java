package com.zccoder.demo.dubbo.api.user;

import com.zccoder.demo.dubbo.api.user.bo.ListReqBo;
import com.zccoder.demo.dubbo.api.user.bo.ListRspBo;
import com.zccoder.demo.dubbo.api.user.bo.SaveAndHelloReqBo;
import com.zccoder.demo.dubbo.api.user.bo.SaveAndHelloRspBo;

/**
 * 用户服务
 *
 * @author zc 2017-09-15
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
