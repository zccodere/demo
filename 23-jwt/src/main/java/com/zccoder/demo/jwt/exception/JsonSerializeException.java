package com.zccoder.demo.jwt.exception;

/**
 * JSON序列化异常，对象转JSON字符串异常
 *
 * @author zc
 * @date 2020/09/04
 */
public class JsonSerializeException extends RuntimeException {

    private static final long serialVersionUID = 2848240744976907885L;

    public JsonSerializeException(String message, Throwable cause) {
        super(message, cause);
    }

}
