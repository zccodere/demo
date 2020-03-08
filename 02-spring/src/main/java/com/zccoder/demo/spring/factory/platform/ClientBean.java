package com.zccoder.demo.spring.factory.platform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 调用端
 *
 * @author zc 2020-03-02
 */
@Component
public class ClientBean {

    @Autowired
    private PlatformManager platformManager;

    public void doTest() {
        System.out.println(this.platformManager.getPlatformName(1));
        System.out.println(this.platformManager.getPlatformName(2));
        System.out.println(this.platformManager.doOtherMethod(1));
        System.out.println(this.platformManager.doOtherMethod(2));
    }
}
