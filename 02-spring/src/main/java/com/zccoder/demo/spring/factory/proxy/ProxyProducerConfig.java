package com.zccoder.demo.spring.factory.proxy;

/**
 * 代理生产者配置
 *
 * @author zc 2018-06-28
 **/
public class ProxyProducerConfig implements ProxyProducerStrategy {

    /**
     * 是否启用插件（缺省值：false）
     */
    private Boolean enable;
    /**
     * 生产者策略
     */
    private Class<? extends ProxyProducer> strategy;

    @Override
    public Class<? extends ProxyProducer> getStrategy() {
        return strategy;
    }

    public void setStrategy(Class<? extends ProxyProducer> strategy) {
        this.strategy = strategy;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
