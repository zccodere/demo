package com.zccoder.demo.spring.ordered;

import org.springframework.core.Ordered;

/**
 * 消息处理器
 *
 * @author zc
 * @date 2020/07/29
 */
public interface MessageHandler extends Ordered {

    /**
     * 匹配是否由该处理器负责处理
     *
     * @param message 消息
     * @return true：是；false：否
     */
    boolean match(String message);

    /**
     * 处理消息
     *
     * @param message 消息
     */
    void doMessage(String message);

}
