package com.zccoder.demo.dubbo.api.user.bo;

import com.zccoder.demo.dubbo.common.bo.RequestBaseBo;

/**
 * 获取所有用户信息入参Bo
 *
 * @author zc 2017-09-17
 */
public class ListReqBo extends RequestBaseBo {

    /**
     * 可选  根据用户名进行模糊匹配查询
     */
    private String name;
    /**
     * 可选  根据登录密码匹配查询
     */
    private String password;

    @Override
    public String toString() {
        // 当有父类时，先调父类的toString方法
        return super.toString() + "ListReqBo [name=" + name + ", password=" + password + "]";
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
