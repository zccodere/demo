package com.zccoder.demo.dubbo.common.bo;

import java.io.Serializable;

import com.zccoder.demo.dubbo.common.enums.ResponseEnum;

/**
 * 基础业务模型类
 *
 * @author zc 2017-09-15
 */
public class ResponseBaseBo implements Serializable {

    /**
     * 方法处理响应结果
     */
    private ResponseEnum rspStatus;

    @Override
    public String toString() {
        return "RspBaseBo [rspStatus=" + String.valueOf(rspStatus.getCode() + " " + rspStatus.getDesc()) + "]";
    }

    public ResponseBaseBo() {

    }

    public ResponseBaseBo(ResponseEnum rspStatus) {
        this.rspStatus = rspStatus;
    }

    public ResponseEnum getRspStatus() {
        return rspStatus;
    }

    public void setRspStatus(ResponseEnum rspStatus) {
        this.rspStatus = rspStatus;
    }

}
