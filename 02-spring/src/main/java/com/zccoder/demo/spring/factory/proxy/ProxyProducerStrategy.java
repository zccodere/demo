package com.zccoder.demo.spring.factory.proxy;

/**
 * 标题：代理生产者策略<br>
 * 描述：代理生产者策略<br>
 * 时间：2018/06/28<br>
 *
 * @author zc
 **/
public interface ProxyProducerStrategy {

    /**
     * 获取生产者策略
     *
     * @return 生产者策略
     */
    Class<? extends ProxyProducer> getStrategy();

}
