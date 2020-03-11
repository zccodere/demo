package com.zccoder.demo.unionpay.service.bo;

import java.io.Serializable;

/**
 * 消费服务（支付订单）入参BO
 *
 * @author zc 2018-09-26
 **/
public class ConsumerReqBo implements Serializable {

    private static final long serialVersionUID = -8325053080392591491L;

    /**
     * 商户号
     */
    private String merId;
    /**
     * 交易金额（单位：分）
     */
    private String txnAmt;
    /**
     * 商户订单号（即支付订单号）
     */
    private String orderId;
    /**
     * 订单发送时间（格式：YYYYMMDDhhmmss）
     */
    private String txnTime;

    @Override
    public String toString() {
        return "ConsumerReqBO{" +
                "merId='" + merId + '\'' +
                ", txnAmt='" + txnAmt + '\'' +
                ", orderId='" + orderId + '\'' +
                ", txnTime='" + txnTime + '\'' +
                '}';
    }

    /**
     * 获取 商户代码
     *
     * @return merId 商户代码
     */
    public String getMerId() {
        return this.merId;
    }

    /**
     * 设置 商户代码
     *
     * @param merId 商户代码
     */
    public void setMerId(String merId) {
        this.merId = merId;
    }

    /**
     * 获取 交易金额（单位：分）
     *
     * @return txnAmt 交易金额（单位：分）
     */
    public String getTxnAmt() {
        return this.txnAmt;
    }

    /**
     * 设置 交易金额（单位：分）
     *
     * @param txnAmt 交易金额（单位：分）
     */
    public void setTxnAmt(String txnAmt) {
        this.txnAmt = txnAmt;
    }

    /**
     * 获取 商户订单号
     *
     * @return orderId 商户订单号
     */
    public String getOrderId() {
        return this.orderId;
    }

    /**
     * 设置 商户订单号
     *
     * @param orderId 商户订单号
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取 订单发送时间（格式：YYYYMMDDhhmmss）
     *
     * @return txnTime 订单发送时间（格式：YYYYMMDDhhmmss）
     */
    public String getTxnTime() {
        return this.txnTime;
    }

    /**
     * 设置 订单发送时间（格式：YYYYMMDDhhmmss）
     *
     * @param txnTime 订单发送时间（格式：YYYYMMDDhhmmss）
     */
    public void setTxnTime(String txnTime) {
        this.txnTime = txnTime;
    }
}
