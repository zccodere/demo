package com.zccoder.demo.pay.executor;

/**
 * 标题：支付机构抽象实现<br>
 * 描述：支付机构抽象实现<br>
 * 时间：2018/09/27<br>
 *
 * @author zc
 **/
public abstract class AbstractPaymentExecutor implements PaymentExecutor {

    @Override
    public void pay(String payMethod, String orderId) {
        if (this.doMatch(payMethod)) {
            this.doPay(orderId);
            return;
        }
        if (nextExecutor == null) {
            throw new RuntimeException("不支持的支付方式：" + payMethod);
        }
        this.nextExecutor.pay(payMethod, orderId);
    }

    @Override
    public void refund(String payMethod, String orderId) {
        if (this.doMatch(payMethod)) {
            this.doRefund(orderId);
            return;
        }
        if (nextExecutor == null) {
            throw new RuntimeException("不支持的支付方式：" + payMethod);
        }
        this.nextExecutor.refund(payMethod, orderId);
    }

    @Override
    public void query(String payMethod, String orderId) {
        if (this.doMatch(payMethod)) {
            this.doQuery(orderId);
            return;
        }
        if (nextExecutor == null) {
            throw new RuntimeException("不支持的支付方式：" + payMethod);
        }
        this.nextExecutor.query(payMethod, orderId);
    }

    @Override
    public void bill(String payMethod, String orderId) {
        if (this.doMatch(payMethod)) {
            this.doBill(orderId);
            return;
        }
        if (nextExecutor == null) {
            throw new RuntimeException("不支持的支付方式：" + payMethod);
        }
        this.nextExecutor.bill(payMethod, orderId);
    }

    private AbstractPaymentExecutor nextExecutor;

    public void setNextExecutor(AbstractPaymentExecutor nextExecutor) {
        this.nextExecutor = nextExecutor;
    }

    /**
     * 匹配支付方式
     *
     * @param payMethod 支付方式
     * @return true：匹配；false：不匹配
     */
    protected abstract Boolean doMatch(String payMethod);

    /**
     * 执行支付
     *
     * @param orderId 订单ID
     */
    protected abstract void doPay(String orderId);

    /**
     * 执行退款
     *
     * @param orderId 订单ID
     */
    protected abstract void doRefund(String orderId);

    /**
     * 执行查询
     *
     * @param orderId 订单ID
     */
    protected abstract void doQuery(String orderId);

    /**
     * 执行账单
     *
     * @param orderId 订单ID
     */
    protected abstract void doBill(String orderId);

}
