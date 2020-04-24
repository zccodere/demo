package com.zccoder.demo.spring.aop.mark;

import org.springframework.stereotype.Component;

/**
 * 商品批量查询接口处理器
 *
 * @author zc 2020-04-21
 */
@Component
public class ItemBatchPutHandler implements MethodHandler {

    @Mark
    @Override
    public String doService(String request) {
        // 该方法会被拦截
        System.out.println("put handler：" + request);
        return "success";
    }
}
