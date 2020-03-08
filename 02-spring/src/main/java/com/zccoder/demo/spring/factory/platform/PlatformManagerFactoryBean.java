package com.zccoder.demo.spring.factory.platform;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;
import java.util.List;

/**
 * 平台服务工厂Bean
 *
 * @author zc 2020-03-02
 */
@Component
public class PlatformManagerFactoryBean implements FactoryBean<PlatformManager> {

    @Autowired
    private List<PlatformAble> platformAbleList;

    @Override
    public PlatformManager getObject() {
        PlatformHandler handler = new PlatformHandler();
        handler.setPlatformAbleList(platformAbleList);
        return (PlatformManager) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{PlatformManager.class}, handler);
    }

    @Override
    public Class<?> getObjectType() {
        return PlatformManager.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
