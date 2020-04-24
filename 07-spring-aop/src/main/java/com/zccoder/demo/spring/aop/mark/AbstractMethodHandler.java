package com.zccoder.demo.spring.aop.mark;

/**
 * API接口处理器抽象实现
 *
 * @author zc 2020-04-20
 */
public abstract class AbstractMethodHandler implements MethodHandler {

    @Override
    public String doService(String request) {
        System.out.println("AbstractMethodHandler:doService");
        return executeService(request);
    }

    /**
     * 执行接口方法
     *
     * @param request 请求参数
     * @return 响应参数
     */
    @Mark
    protected abstract String executeService(String request);
}
