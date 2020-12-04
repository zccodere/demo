package com.zccoder.demo.jwt.domain;

import com.zccoder.demo.jwt.dto.LoginDto;
import com.zccoder.demo.jwt.dto.LoginSuccessDto;

import java.io.Serializable;

import lombok.Data;

/**
 * 用户信息
 *
 * @author zc
 * @date 2020/09/29
 */
@Data
public class UserAll implements Serializable {

    private static final long serialVersionUID = 5559723413981916387L;

    /**
     * 用户编号
     */
    private Integer userId;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 登录密码
     */
    private String password;

    public static UserAll of(LoginDto dto) {
        UserAll user = new UserAll();
        user.setUserName(dto.getName());
        user.setPassword(dto.getPassword());
        return user;
    }

    public User toUser() {
        User user = new User();
        user.setUserId(this.userId);
        user.setUserName(this.userName);
        return user;
    }

    public LoginSuccessDto toLoginSuccessDto() {
        LoginSuccessDto user = new LoginSuccessDto();
        user.setUserId(this.userId);
        user.setUserName(this.userName);
        return user;
    }

}
