package com.zccoder.demo.jwt.exception;

import com.zccoder.demo.jwt.constant.DmoJwtErrorCode;

/**
 * 自定义项目顶级异常
 *
 * @author zc
 * @date 2020/09/29
 */
public class DemoJwtException extends RuntimeException {

    /**
     * 错误编码
     */
    private String statusCode;

    public DemoJwtException(DmoJwtErrorCode errorCode) {
        super(errorCode.getMessage());
        this.statusCode = errorCode.getStatusCode();
    }

    public DemoJwtException(DmoJwtErrorCode errorCode, Object... args) {
        super(String.format(errorCode.getMessage(), args));
        this.statusCode = errorCode.getStatusCode();
    }

    public String getStatusCode() {
        return statusCode;
    }

}
