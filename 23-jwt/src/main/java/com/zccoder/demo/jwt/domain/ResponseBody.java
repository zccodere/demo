package com.zccoder.demo.jwt.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 接口响应基类
 *
 * @author zc
 * @date 2020/09/29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBody<T, M extends EmptyMeta> {

    /**
     * 成功
     */
    public static final String SUCCESS_MESSAGE = "成功";
    /**
     * 成功业务状态码
     */
    public static final String SUCCESS_STATUS_CODE = "200";
    /**
     * 失败
     */
    public static final String FAIL_MESSAGE = "失败";
    /**
     * 失败业务状态码
     */
    public static final String FAIL_STATUS_CODE = "400";

    /**
     * 状态码
     */
    private String statusCode;
    /**
     * 消息
     */
    private String message;
    /**
     * 具体接口数据
     */
    private T data;
    /**
     * 元数据
     */
    private M meta;

    public static <T, M extends EmptyMeta> ResponseBody<T, M> success() {
        return new ResponseBody<>(SUCCESS_STATUS_CODE, SUCCESS_MESSAGE, null, null);
    }

    public static <T, M extends EmptyMeta> ResponseBody<T, M> success(T data) {
        return new ResponseBody<>(SUCCESS_STATUS_CODE, SUCCESS_MESSAGE, data, null);
    }

    public static <T, M extends EmptyMeta> ResponseBody<T, M> fail(String statusCode, String message) {
        return new ResponseBody<>(statusCode, message, null, null);
    }

}
