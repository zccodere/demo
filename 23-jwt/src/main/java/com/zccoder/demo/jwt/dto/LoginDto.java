package com.zccoder.demo.jwt.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Data;

/**
 * 用户名密码登录
 *
 * @author zc
 * @date 2020/09/29
 */
@Data
public class LoginDto implements Serializable {

    private static final long serialVersionUID = -7052914599395046L;

    /**
     * 用户名
     */
    @NotBlank
    private String name;
    /**
     * 密码
     */
    @NotBlank
    private String password;

}
