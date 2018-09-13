package com.zccoder.demo.redis.mq.data.controller;

import com.zccoder.demo.redis.mq.data.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 标题：消息发送controller<br>
 * 描述：消息发送controller<br>
 * 时间：2018/06/22<br>
 *
 * @author zc
 **/
@RestController
public class ProducerController {

    @Autowired
    private MessageProducer messageProducer;

    @GetMapping("/send/{message}")
    public Object send(@PathVariable("message") String message) {
        this.messageProducer.publish(message);
        return Boolean.TRUE;
    }

}
