package com.zccoder.demo.pay.executor;

import com.zccoder.demo.pay.executor.support.AliPayPaymentExecutor;
import com.zccoder.demo.pay.executor.support.UnionPaymentExecutor;
import com.zccoder.demo.pay.executor.support.WxPayPaymentExecutor;

/**
 * 标题：支付机构工厂<br>
 * 描述：支付机构工厂<br>
 * 时间：2018/09/27<br>
 *
 * @author zc
 **/
public class PaymentExecutorFactory {

    private static PaymentExecutor payment;

    static {
        AbstractPaymentExecutor aliPayPaymentExecutor = new AliPayPaymentExecutor();
        AbstractPaymentExecutor wxPayPaymentExecutor = new WxPayPaymentExecutor();
        AbstractPaymentExecutor unionPaymentExecutor = new UnionPaymentExecutor();

        wxPayPaymentExecutor.setNextExecutor(unionPaymentExecutor);
        aliPayPaymentExecutor.setNextExecutor(wxPayPaymentExecutor);
        payment = aliPayPaymentExecutor;
    }

    public static PaymentExecutor getPaymentExecutor() {
        return payment;
    }
}
