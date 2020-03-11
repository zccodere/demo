package com.zccoder.demo.unionpay.service.bo;

import java.io.Serializable;

/**
 * 消费撤销服务（退款订单）入参BO
 *
 * @author zc 2018-09-26
 **/
public class ConsumerUndoReqBo implements Serializable {

    private static final long serialVersionUID = 7769621937561024134L;
    /**
     * 商户号
     */
    private String merId;
    /**
     * 原交易流水号（即银联交易成功的流水号）
     */
    private String origQryId;
    /**
     * 交易金额（单位：分；备注：需与原订单号金额一致）
     */
    private String txnAmt;
    /**
     * 商户订单号（备注：需新生成；即退款订单号）
     */
    private String orderId;
    /**
     * 订单发送时间（格式：YYYYMMDDhhmmss）
     */
    private String txnTime;

    @Override
    public String toString() {
        return "ConsumerUndoReqBO{" +
                "merId='" + merId + '\'' +
                ", origQryId='" + origQryId + '\'' +
                ", txnAmt='" + txnAmt + '\'' +
                ", orderId='" + orderId + '\'' +
                ", txnTime='" + txnTime + '\'' +
                '}';
    }

    /**
     * 获取 商户号
     *
     * @return merId 商户号
     */
    public String getMerId() {
        return this.merId;
    }

    /**
     * 设置 商户号
     *
     * @param merId 商户号
     */
    public void setMerId(String merId) {
        this.merId = merId;
    }

    /**
     * 获取 原交易流水号（即银联交易成功的流水号）
     *
     * @return origQryId 原交易流水号（即银联交易成功的流水号）
     */
    public String getOrigQryId() {
        return this.origQryId;
    }

    /**
     * 设置 原交易流水号（即银联交易成功的流水号）
     *
     * @param origQryId 原交易流水号（即银联交易成功的流水号）
     */
    public void setOrigQryId(String origQryId) {
        this.origQryId = origQryId;
    }

    /**
     * 获取 交易金额（单位：分；备注：需与原订单号金额一致）
     *
     * @return txnAmt 交易金额（单位：分；备注：需与原订单号金额一致）
     */
    public String getTxnAmt() {
        return this.txnAmt;
    }

    /**
     * 设置 交易金额（单位：分；备注：需与原订单号金额一致）
     *
     * @param txnAmt 交易金额（单位：分；备注：需与原订单号金额一致）
     */
    public void setTxnAmt(String txnAmt) {
        this.txnAmt = txnAmt;
    }

    /**
     * 获取 商户订单号（备注：需新生成；即退款订单号）
     *
     * @return orderId 商户订单号（备注：需新生成；即退款订单号）
     */
    public String getOrderId() {
        return this.orderId;
    }

    /**
     * 设置 商户订单号（备注：需新生成；即退款订单号）
     *
     * @param orderId 商户订单号（备注：需新生成；即退款订单号）
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
