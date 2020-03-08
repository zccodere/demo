package com.zccoder.demo.spring.factory.platform;

import org.springframework.stereotype.Component;

/**
 * 京东平台
 *
 * @author zc 2020-03-02
 */
@Component
public class JdPlatform implements PlatformAble {

    @Override
    public int getPlatformId() {
        return 2;
    }

    @Override
    public String getPlatformName(Integer platformId) {
        return "京东平台";
    }

    @Override
    public String doOtherMethod(Integer platformId) {
        return "京东平台执行其他方法";
    }
}