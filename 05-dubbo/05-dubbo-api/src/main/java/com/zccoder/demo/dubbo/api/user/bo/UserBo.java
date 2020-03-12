package com.zccoder.demo.dubbo.api.user.bo;

import java.io.Serializable;

/**
 * 用户类
 *
 * @author zc 2017-09-17
 */
public class UserBo implements Serializable {

    /**
     * 编号
     */
    private Long id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 登录密码
     */
    private String password;

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", password=" + password + "]";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}