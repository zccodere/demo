package com.zccoder.demo.spring.factory.proxy;

/**
 * 代理生产者策略
 *
 * @author zc 2018-06-28
 **/
public interface ProxyProducerStrategy {

    /**
     * 获取生产者策略
     *
     * @return 生产者策略
     */
    Class<? extends ProxyProducer> getStrategy();

}
