package com.zccoder.demo.dubbo.api.user.bo;

import com.zccoder.demo.dubbo.common.bo.ResponseBaseBo;

/**
 * 保存用户信息并对用户名说Hello出参Bo
 *
 * @author zc 2017-09-15
 */
public class SaveAndHelloRspBo extends ResponseBaseBo {

    /**
     * 用户ID，方法执行成功时返回
     */
    private String id;
    /**
     * Hello信息，方法执行成功时返回
     */
    private String info;

    @Override
    public String toString() {
        return super.toString() + "SaveAndHelloRspBo [id=" + id + ", info=" + info + "]";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
