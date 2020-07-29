package com.zccoder.demo.spring.ordered;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * 第三个消息处理器
 *
 * @author zc
 * @date 2020/07/29
 */
@Component
public class ThreeMessageHandler implements MessageHandler {

    public static final int ORDERED = Ordered.LOWEST_PRECEDENCE;

    @Override
    public boolean match(String message) {
        return true;
    }

    @Override
    public void doMessage(String message) {
        System.out.println("three");
    }

    @Override
    public int getOrder() {
        return ORDERED;
    }
}
