package com.zccoder.demo.dubbo.api.user.bo;

import com.zccoder.demo.dubbo.common.bo.RequestBaseBo;

/**
 * 保存用户信息并对用户名说Hello入参Bo
 *
 * @author zc 2017-09-15
 */
public class SaveAndHelloReqBo extends RequestBaseBo {

    private static final long serialVersionUID = 1L;

    /**
     * 必传  用户名
     */
    private String name;
    /**
     * 必传  登录密码
     */
    private String password;

    @Override
    public String toString() {
        // 当有父类时，先调父类的toString方法
        return super.toString() + "SaveAndHelloReqBo [name=" + name + ", password=" + password + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
