package com.zccoder.demo.spring.factory.platform;

import org.springframework.stereotype.Component;

/**
 * 阿里平台
 *
 * @author zc 2020-03-02
 */
@Component
public class AliPlatform implements PlatformAble {

    @Override
    public int getPlatformId() {
        return 1;
    }

    @Override
    public String getPlatformName(Integer platformId) {
        return "阿里平台";
    }

    @Override
    public String doOtherMethod(Integer platformId) {
        return "阿里平台执行其他方法";
    }
}