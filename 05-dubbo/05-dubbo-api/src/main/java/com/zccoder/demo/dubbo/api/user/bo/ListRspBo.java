package com.zccoder.demo.dubbo.api.user.bo;

import com.zccoder.demo.dubbo.common.bo.ResponseBaseBo;

import java.util.List;

/**
 * 获取所有用户信息出参Bo
 *
 * @author zc 2017-09-17
 */
public class ListRspBo extends ResponseBaseBo {

    /**
     * 用户信息集合，当查询数据为空时，返回空集合
     */
    private List<UserBo> users;

    @Override
    public String toString() {
        return "ListRspBo [users=" + users + "]";
    }

    public List<UserBo> getUsers() {
        return users;
    }

    public void setUsers(List<UserBo> users) {
        this.users = users;
    }
}
