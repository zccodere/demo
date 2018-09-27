package com.zccoder.demo.pay;

import com.zccoder.demo.pay.constant.PayConstants;
import com.zccoder.demo.pay.executor.PaymentExecutor;
import com.zccoder.demo.pay.executor.PaymentExecutorFactory;

/**
 * 标题：测试类<br>
 * 描述：测试类<br>
 * 时间：2018/09/27<br>
 *
 * @author zc
 **/
public class Main {

    public static void main(String[] args) {

        PaymentExecutor payment = PaymentExecutorFactory.getPaymentExecutor();

        payment.pay(PayConstants.PayMethod.ALI_PAY.getPayMethod(), "1001");
        payment.pay(PayConstants.PayMethod.WX_PAY.getPayMethod(), "1001");
        payment.pay(PayConstants.PayMethod.UNION_PAY.getPayMethod(), "1001");

        payment.refund(PayConstants.PayMethod.ALI_PAY.getPayMethod(), "1001");
        payment.refund(PayConstants.PayMethod.WX_PAY.getPayMethod(), "1001");
        payment.refund(PayConstants.PayMethod.UNION_PAY.getPayMethod(), "1001");

        payment.query(PayConstants.PayMethod.ALI_PAY.getPayMethod(), "1001");
        payment.query(PayConstants.PayMethod.WX_PAY.getPayMethod(), "1001");
        payment.query(PayConstants.PayMethod.UNION_PAY.getPayMethod(), "1001");

        payment.bill(PayConstants.PayMethod.ALI_PAY.getPayMethod(), "1001");
        payment.bill(PayConstants.PayMethod.WX_PAY.getPayMethod(), "1001");
        payment.bill(PayConstants.PayMethod.UNION_PAY.getPayMethod(), "1001");
    }
}
