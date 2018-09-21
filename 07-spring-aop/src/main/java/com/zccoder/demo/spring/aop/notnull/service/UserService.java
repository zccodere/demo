package com.zccoder.demo.spring.aop.notnull.service;

import com.zccoder.demo.spring.aop.notnull.aspect.RespMessage;

/**
 * 用户 Service
 *
 * @author ZhangCheng
 * @version V1.0
 * @date 2017-03-20
 */
public interface UserService {

    /**
     * 通过名称查询用户
     *
     * @param jsessionId 会话ID
     * @param userName   名称
     * @return 用户
     * @throws Exception 异常
     */
    RespMessage getUserInfoByName(String jsessionId, String userName) throws Exception;

    /**
     * 通过ID查询用户
     *
     * @param jsessionId 会话ID
     * @param userId     ID
     * @return 用户
     */
    RespMessage getUserInfoById(String jsessionId,String userId);
}
