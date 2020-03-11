package com.zccoder.demo.nacos.api;

/**
 * 常量类
 *
 * @author zc 2019-06-19
 */
public class HelloApiConst {

    /**
     * 服务名称
     */
    public static final String SERVICE_NAME = "nacos-provider";
    /**
     * 服务地址
     */
    public static final String SERVICE_URL = "${nacos.provider.url:}";

}
