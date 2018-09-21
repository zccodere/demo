package com.zccoder.demo.spring.aop.log.service;

import com.zccoder.demo.spring.aop.log.dto.ReturnMessage;

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
    ReturnMessage getUserInfoByName(String jsessionId, String userName) throws Exception;

    /**
     * 通过ID查询用户
     *
     * @param jsessionId 会话ID
     * @param userId     ID
     * @return 用户
     */
    ReturnMessage getUserInfoById(String jsessionId, String userId);
}
