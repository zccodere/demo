package com.zccoder.demo.pay.executor;

/**
 * 标题：支付机构接口<br>
 * 描述：支付机构接口<br>
 * 时间：2018/09/27<br>
 *
 * @author zc
 **/
public interface PaymentExecutor {

    /**
     * 支付
     *
     * @param payMethod 支付方式
     * @param orderId   订单ID
     */
    void pay(String payMethod, String orderId);

    /**
     * 退款
     *
     * @param payMethod 支付方式
     * @param orderId   订单ID
     */
    void refund(String payMethod, String orderId);

    /**
     * 查询
     *
     * @param payMethod 支付方式
     * @param orderId   订单ID
     */
    void query(String payMethod, String orderId);

    /**
     * 账单
     *
     * @param payMethod 支付方式
     * @param orderId   订单ID
     */
    void bill(String payMethod, String orderId);
}
