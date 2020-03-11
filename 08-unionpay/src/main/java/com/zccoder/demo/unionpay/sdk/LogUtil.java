package com.zccoder.demo.unionpay.sdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Map.Entry;

/**
 * acp sdk 日志工具类
 *
 * @author zc 2018-09-27
 **/
public class LogUtil {

    private final static Logger ACP_SDK_LOG = LoggerFactory.getLogger("ACP_SDK_LOG");
    private final static Logger SDK_ERR_LOG = LoggerFactory.getLogger("SDK_ERR_LOG");
    private final static Logger SDK_MSG_LOG = LoggerFactory.getLogger("SDK_MSG_LOG");

    private final static String LOG_STRING_REQ_MSG_BEGIN = "============================== SDK REQ MSG BEGIN ==============================";
    private final static String LOG_STRING_REQ_MSG_END = "==============================  SDK REQ MSG END  ==============================";
    private final static String LOG_STRING_RSP_MSG_BEGIN = "============================== SDK RSP MSG BEGIN ==============================";
    private final static String LOG_STRING_RSP_MSG_END = "==============================  SDK RSP MSG END  ==============================";

    /**
     * 记录普通日志
     */
    public static void writeLog(String cont) {
        ACP_SDK_LOG.info(cont);
    }

    /**
     * 记录ERORR日志
     */
    public static void writeErrorLog(String cont) {
        SDK_ERR_LOG.error(cont);
    }

    /**
     * 记录ERROR日志
     */
    public static void writeErrorLog(String cont, Throwable ex) {
        SDK_ERR_LOG.error(cont, ex);
    }

    /**
     * 记录通信报文
     */
    public static void writeMessage(String msg) {
        SDK_MSG_LOG.info(msg);
    }

    /**
     * 打印请求报文
     */
    public static void printRequestLog(Map<String, String> reqParam) {
        writeMessage(LOG_STRING_REQ_MSG_BEGIN);
        for (Entry<String, String> en : reqParam.entrySet()) {
            writeMessage("[" + en.getKey() + "] = [" + en.getValue() + "]");
        }
        writeMessage(LOG_STRING_REQ_MSG_END);
    }

    /**
     * 打印响应报文.
     */
    public static void printResponseLog(String res) {
        writeMessage(LOG_STRING_RSP_MSG_BEGIN);
        writeMessage(res);
        writeMessage(LOG_STRING_RSP_MSG_END);
    }

    /**
     * debug方法
     */
    public static void debug(String cont) {
        if (ACP_SDK_LOG.isDebugEnabled()) {
            ACP_SDK_LOG.debug(cont);
        }
    }
}
