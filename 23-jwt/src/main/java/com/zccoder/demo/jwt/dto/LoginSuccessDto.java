package com.zccoder.demo.jwt.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * 登录成功返回用户信息
 *
 * @author zc
 * @date 2020/09/29
 */
@Data
public class LoginSuccessDto implements Serializable {

    private static final long serialVersionUID = -4263503229798080914L;

    /**
     * 授权令牌
     */
    private String token;
    /**
     * 用于刷新授权的令牌
     */
    private String refreshToken;

    /**
     * 用户编号
     */
    private Integer userId;
    /**
     * 用户名称
     */
    private String userName;

}
