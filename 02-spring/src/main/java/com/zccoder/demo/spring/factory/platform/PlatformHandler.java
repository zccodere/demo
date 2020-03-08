package com.zccoder.demo.spring.factory.platform;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 平台接口代理实现
 *
 * @author zc 2020-03-02
 */
public class PlatformHandler implements InvocationHandler {

    private List<PlatformAble> platformAbleList;

    public void setPlatformAbleList(List<PlatformAble> platformAbleList) {
        this.platformAbleList = platformAbleList;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Integer id = (Integer) args[0];
        PlatformAble platform = getPlatform(id);
        return method.invoke(platform, args);
    }

    private PlatformAble getPlatform(Integer id) {
        for (PlatformAble platformAble : platformAbleList) {
            if (platformAble.getPlatformId() == id) {
                return platformAble;
            }
        }
        throw new RuntimeException("平台ID[" + id + "]暂不支持");
    }
}
