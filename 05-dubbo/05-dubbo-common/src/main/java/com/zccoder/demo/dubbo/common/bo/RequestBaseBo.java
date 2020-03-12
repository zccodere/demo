package com.zccoder.demo.dubbo.common.bo;

import java.io.Serializable;

/**
 * 基础业务模型类
 *
 * @author zc 2017-09-15
 */
public class RequestBaseBo implements Serializable {

    /**
     * 内部接口流转标识
     */
    private String transId;

    @Override
    public String toString() {
        return "ReqBaseBo [transId=" + transId + "]";
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }
}
