package com.zccoder.demo.spring.factory.proxy;

/**
 * 标题：代理生产者配置<br>
 * 描述：代理生产者配置<br>
 * 时间：2018/06/28<br>
 *
 * @author zc
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
