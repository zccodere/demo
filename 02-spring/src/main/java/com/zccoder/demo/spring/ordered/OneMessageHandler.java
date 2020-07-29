package com.zccoder.demo.spring.ordered;

import org.springframework.stereotype.Component;

/**
 * 第一个消息处理器
 *
 * @author zc
 * @date 2020/07/29
 */
@Component
public class OneMessageHandler implements MessageHandler {

    public static final int ORDERED = TwoMessageHandler.ORDERED - 1;

    @Override
    public boolean match(String message) {
        return true;
    }

    @Override
    public void doMessage(String message) {
        System.out.println("one");
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
