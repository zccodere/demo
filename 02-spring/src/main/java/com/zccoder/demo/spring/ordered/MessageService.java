package com.zccoder.demo.spring.ordered;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 消息服务层
 *
 * @author zc
 * @date 2020/07/29
 */
@Service
public class MessageService {

    @Autowired
    private List<MessageHandler> messageHandlerList;

    public void doMessage(String message) {
        for (MessageHandler messageHandler : messageHandlerList) {
            if (messageHandler.match(message)) {
                messageHandler.doMessage(message);
            }
        }
    }
}
