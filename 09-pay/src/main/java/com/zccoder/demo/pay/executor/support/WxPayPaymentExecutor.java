package com.zccoder.demo.pay.executor.support;

import com.zccoder.demo.pay.constant.PayConstants;
import com.zccoder.demo.pay.executor.AbstractPaymentExecutor;

/**
 * 标题：微信支付<br>
 * 描述：微信支付<br>
 * 时间：2018/09/27<br>
 *
 * @author zc
 **/
public class WxPayPaymentExecutor extends AbstractPaymentExecutor {

    @Override
    protected Boolean doMatch(String payMethod) {
        if (PayConstants.PayMethod.WX_PAY.getPayMethod().equals(payMethod)) {
            return true;
        }
        return false;
    }

    @Override
    protected void doPay(String orderId) {
        System.out.println("微信支付支付订单：" + orderId);
    }

    @Override
    protected void doRefund(String orderId) {
        System.out.println("微信支付退款订单：" + orderId);
    }

    @Override
    protected void doQuery(String orderId) {
        System.out.println("微信支付查询订单：" + orderId);
    }

    @Override
    protected void doBill(String orderId) {
        System.out.println("微信支付账单文件：" + orderId);
    }

}
