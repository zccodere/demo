package com.zccoder.demo.spring.aop.mark;

import org.springframework.stereotype.Component;

/**
 * 商品批量查询接口处理器
 *
 * @author zc 2020-04-21
 */
@Component
public class ItemBatchGetHandler extends AbstractMethodHandler {

    @Mark
    @Override
    public String doService(String request) {
        // 该方法会被拦截
        return super.doService(request);
    }

    @Mark
    @Override
    protected String executeService(String request) {
        // 该方法不会被拦截
        return "success-ItemBatchGetHandler";
    }

}
