package com.zccoder.demo.jwt.constant;

import lombok.Getter;

/**
 * 项目错误编码枚举类
 *
 * @author zc
 * @date 2020/09/29
 */
@Getter
public enum DmoJwtErrorCode {

    // ***********************************************************************
    // 编写规则
    // 按功能模块分区域编写，错误以demo.开头
    // ***********************************************************************

    // ***********************************************************************
    //                          用户注册登录相关错误编码
    // ***********************************************************************

    USER_LOGIN_NAME_NOT_REGISTER("demo.user_login_name_not_register", "用户名未注册！"),

    USER_LOGIN_PASSWORD_ERROR("demo.user_login_password_error", "登录密码错误！"),

    USER_LOGIN_REPEAT("demo.user_login_repeat", "当前账号已登录！"),

    USER_REFRESH_TOKEN_TIMEOUT("401", "身份过期！"),

    USER_REFRESH_TOKEN_INVALID("401", "认证失败！"),

    USER_REGISTER_NAME_REPEAT("demo.user_register_name_repeat", "用户名已存在！");

    /**
     * 错误编码
     */
    private String statusCode;
    /**
     * 错误提示
     */
    private String message;

    DmoJwtErrorCode(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

}
