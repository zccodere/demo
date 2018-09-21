package com.zccoder.demo.spring.aop.notnull.aspect;

import java.io.Serializable;
import java.util.Map;

/**
 * 方法出参
 *
 * @author ZhangCheng
 * @version V1.0
 * @date 2017-03-20
 */
public class RespMessage implements Serializable {

    private static final long serialVersionUID = -1689104493218325358L;

    private String code;

    private String msg;

    private Map<String, Object> args;

    @Override
    public String toString() {
        return "ReturnMessage [code=" + code + ", msg=" + msg + ", args=" + args
                + "]";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getArgs() {
        return args;
    }

    public void setArgs(Map<String, Object> args) {
        this.args = args;
    }


}
