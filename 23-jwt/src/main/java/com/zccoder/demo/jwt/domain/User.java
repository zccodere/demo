package com.zccoder.demo.jwt.domain;

import com.zccoder.demo.jwt.dto.LoginDto;

import java.io.Serializable;

import lombok.Data;

/**
 * 用户信息
 *
 * @author zc
 * @date 2020/09/29
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 5559723413981916387L;

    /**
     * 用户编号
     */
    private Integer userId;
    /**
     * 用户名称
     */
    private String userName;

}
