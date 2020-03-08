package com.zccoder.demo.redis.mq.data.controller;

import com.zccoder.demo.redis.mq.data.producer.MessageProducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消息发送控制层
 *
 * @author zc 2018-06-22
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
