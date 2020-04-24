package com.zccoder.demo.spring.aop.mark;

/**
 * API接口处理器
 *
 * @author zc 2020-04-17
 */
public interface MethodHandler {

    /**
     * 执行接口方法
     *
     * @param request 请求参数
     * @return 响应参数
     */
    String doService(String request);

}
