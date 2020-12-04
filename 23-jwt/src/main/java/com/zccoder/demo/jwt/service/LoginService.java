package com.zccoder.demo.jwt.service;

import com.zccoder.demo.jwt.dto.LoginDto;
import com.zccoder.demo.jwt.dto.LoginSuccessDto;

/**
 * 登录服务层
 *
 * @author zc
 * @date 2020/09/29
 */
public interface LoginService {

    /**
     * 用户名密码注册
     *
     * @param dto 用户名密码
     */
    void register(LoginDto dto);

    /**
     * 用户名密码登录
     *
     * @param dto 用户名密码
     * @return 登录结果
     */
    LoginSuccessDto login(LoginDto dto);

    /**
     * 刷新授权令牌
     *
     * @param refreshToken 用于刷新授权令牌的令牌
     * @return 新的授权令牌
     */
    LoginSuccessDto refreshToken(String refreshToken);

    /**
     * 退出登录
     *
     * @param token 授权令牌
     */
    void logout(String token);

}
