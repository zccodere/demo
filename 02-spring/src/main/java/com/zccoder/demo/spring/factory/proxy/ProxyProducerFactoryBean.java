package com.zccoder.demo.spring.factory.proxy;

import com.zccoder.demo.spring.factory.producer.DefaultProxyProducer;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;

import java.lang.reflect.Proxy;

/**
 * 生产者工厂Bean
 *
 * @author zc 2018-06-28
 **/
public class ProxyProducerFactoryBean implements FactoryBean<ProxyProducer>, ApplicationContextAware {

    private ApplicationContext applicationContext;
    private ProxyProducerWrapper wrapper;
    private ProxyProducer proxyProducer;
    private ProxyProducerStrategy localStrategy;

    @Override
    public ProxyProducer getObject() {
        ProxyProducerConfig config = applicationContext.getBean(ProxyProducerConfig.class);

        if (config.getEnable() == null) {
            config.setEnable(false);
        }
        if (!config.getEnable()) {
            return new DefaultProxyProducer();
        }
        if (proxyProducer != null) {
            return proxyProducer;
        }

        if (localStrategy == null) {
            // 如果单个生产者未配置消息策略，则使用全局消息策略
            localStrategy = config;
        }
        wrapper = new ProxyProducerWrapper(localStrategy);

        proxyProducer = (ProxyProducer) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{ProxyProducer.class}, (proxy, method, args) -> method.invoke(wrapper, args));

        return proxyProducer;
    }

    @Override
    public Class<?> getObjectType() {
        return ProxyProducer.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void setProxyProducerStrategy(ProxyProducerStrategy proxyProducerStrategy) {
        this.localStrategy = proxyProducerStrategy;
    }
}
