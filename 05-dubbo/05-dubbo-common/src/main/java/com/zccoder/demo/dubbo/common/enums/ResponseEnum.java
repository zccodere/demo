package com.zccoder.demo.dubbo.common.enums;

/**
 * 业务方法响应结果枚举类
 *
 * @author zc 2017-09-15
 */
public enum ResponseEnum {

    /**
     * 成功
     */
    SUCCESS("0000", "成功"),
    /**
     * 系统异常
     */
    EXCEPTION_SYSTEM("1001", "系统异常"),
    /**
     * 业务异常
     */
    EXCEPTION_BUSINESS("1002", "业务异常"),
    /**
     * 系统错误
     */
    ERROR_SYSTEM("1003", "系统错误");

    /**
     * 状态码
     */
    private String code;
    /**
     * 状态描述
     */
    private String desc;

    ResponseEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
