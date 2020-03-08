package com.zccoder.demo.spring.factory.proxy;

/**
 * 代理生产者包装
 *
 * @author zc 2018-06-28
 **/
public class ProxyProducerWrapper implements ProxyProducer {

    /**
     * 生产者
     */
    private ProxyProducer proxyProducer;

    public ProxyProducerWrapper(ProxyProducerStrategy strategy) {
        try {
            this.proxyProducer = strategy.getStrategy().newInstance();
        } catch (Exception ex) {
            throw new RuntimeException("创建生产者实例异常：", ex);
        }
    }

    @Override
    public void send(String message) {
        this.proxyProducer.send(message);
    }
}
