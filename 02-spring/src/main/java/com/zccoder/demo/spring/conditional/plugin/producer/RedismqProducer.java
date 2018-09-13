package com.zccoder.demo.spring.conditional.plugin.producer;

import com.zccoder.demo.spring.conditional.plugin.Producer;

/**
 * 标题：RedisMq生产者<br>
 * 描述：RedisMq生产者<br>
 * 时间：2018/06/28<br>
 *
 * @author zc
 **/
public class RedismqProducer implements Producer {
    @Override
    public String hello(String name) {
        String result = "Redismq say hello to :" + name;
        System.out.println(result);
        return result;
    }
}
