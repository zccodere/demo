package com.zccoder.demo.jwt.exception;

/**
 * JSON转对象异常
 *
 * @author zc 2020-03-23
 */
public class JsonToObjectException extends RuntimeException {

    private static final long serialVersionUID = -7185067236215304275L;

    /**
     * 原JSON数据
     */
    private final String json;

    public JsonToObjectException(String message, Throwable cause, String json) {
        super(message, cause);
        this.json = json;
    }

    public String getJson() {
        return json;
    }

}
