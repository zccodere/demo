package com.zccoder.demo.pay.constant;

/**
 * 标题：常量<br>
 * 描述：常量<br>
 * 时间：2018/09/27<br>
 *
 * @author zc
 **/
public class PayConstants {

    public enum PayMethod {
        /**
         * 支付宝
         */
        ALI_PAY("10"),
        /**
         * 微信支付
         */
        WX_PAY("20"),
        /**
         * 银联网关支付
         */
        UNION_PAY("30");

        private String payMethod;

        PayMethod(String payMethod) {
            this.payMethod = payMethod;
        }

        public String getPayMethod() {
            return payMethod;
        }
    }
}
