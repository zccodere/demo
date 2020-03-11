package com.zccoder.demo.unionpay.sdk;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;

/**
 * acp sdk 发送后台http请求类
 *
 * @author zc 2018-09-27
 **/
class HttpClient {

    /**
     * 目标地址
     */
    private URL url;

    /**
     * 通信连接超时时间
     */
    private int connectionTimeout;

    /**
     * 通信读超时时间
     */
    private int readTimeOut;

    /**
     * 通信结果
     */
    private String result;

    /**
     * 获取通信结果
     */
    String getResult() {
        return result;
    }

    /**
     * 设置通信结果
     */
    private void setResult(String result) {
        this.result = result;
    }

    /**
     * 构造函数
     *
     * @param url               目标地址
     * @param connectionTimeout HTTP连接超时时间
     * @param readTimeOut       HTTP读写超时时间
     */
    HttpClient(String url, int connectionTimeout, int readTimeOut) {
        try {
            this.url = new URL(url);
            this.connectionTimeout = connectionTimeout;
            this.readTimeOut = readTimeOut;
        } catch (MalformedURLException e) {
            LogUtil.writeErrorLog(e.getMessage(), e);
        }
    }

    /**
     * 发送信息到服务端
     */
    int sendPost(Map<String, String> data, String encoding) {
        try {
            HttpURLConnection httpUrlConnection = this.createConnection("POST", encoding);
            if (null == httpUrlConnection) {
                throw new Exception("Create httpURLConnection Failure");
            }
            String sendData = this.getRequestParamString(data, encoding);
            LogUtil.writeLog("请求报文(对每个报文域的值均已做url编码):[" + sendData + "]");
            this.requestServer(httpUrlConnection, sendData,
                    encoding);
            this.setResult(this.response(httpUrlConnection, encoding));
            LogUtil.writeLog("Response message:[" + result + "]");
            return httpUrlConnection.getResponseCode();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 发送信息到服务端 GET方式
     *
     * @return HTTP 响应码
     */
    int sendGet(String encoding) {
        try {
            HttpURLConnection httpUrlConnection = this.createConnection("GET", encoding);
            if (null == httpUrlConnection) {
                throw new Exception("创建联接失败");
            }
            this.setResult(this.response(httpUrlConnection, encoding));
            LogUtil.writeLog("同步返回报文:[" + result + "]");
            return httpUrlConnection.getResponseCode();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * HTTP Post发送消息
     */
    private void requestServer(final URLConnection connection, String message, String encoder) {
        PrintStream out = null;
        try {
            connection.connect();
            out = new PrintStream(connection.getOutputStream(), false, encoder);
            out.print(message);
            out.flush();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            if (null != out) {
                out.close();
            }
        }
    }

    /**
     * 显示Response消息
     */
    private String response(final HttpURLConnection connection, String encoding) throws Exception {
        InputStream in = null;
        StringBuilder sb = new StringBuilder(1024);
        try {
            int success = 200;
            if (success == connection.getResponseCode()) {
                in = connection.getInputStream();
                sb.append(new String(read(in), encoding));
            } else {
                in = connection.getErrorStream();
                sb.append(new String(read(in), encoding));
            }
            LogUtil.writeLog("HTTP Return Status-Code:["
                    + connection.getResponseCode() + "]");
            return sb.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        } finally {
            if (null != in) {
                in.close();
            }
            if (null != connection) {
                connection.disconnect();
            }
        }
    }

    private static byte[] read(InputStream in) throws IOException {
        byte[] buf = new byte[1024];
        int length;
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        while ((length = in.read(buf, 0, buf.length)) > 0) {
            bout.write(buf, 0, length);
        }
        bout.flush();
        return bout.toByteArray();
    }

    /**
     * 创建连接
     */
    private HttpURLConnection createConnection(String requestMethod, String encoding) throws ProtocolException {
        HttpURLConnection httpUrlConnection;
        try {
            httpUrlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            LogUtil.writeErrorLog(e.getMessage(), e);
            return null;
        }
        // 连接超时时间
        httpUrlConnection.setConnectTimeout(this.connectionTimeout);
        // 读取结果超时时间
        httpUrlConnection.setReadTimeout(this.readTimeOut);
        // 可读
        httpUrlConnection.setDoInput(true);
        // 可写
        httpUrlConnection.setDoOutput(true);
        // 取消缓存
        httpUrlConnection.setUseCaches(false);
        httpUrlConnection.setRequestProperty("Content-type",
                "application/x-www-form-urlencoded;charset=" + encoding);
        httpUrlConnection.setRequestMethod(requestMethod);
        String https = "https";
        if (https.equalsIgnoreCase(url.getProtocol())) {
            HttpsURLConnection connection = (HttpsURLConnection) httpUrlConnection;
            //是否验证https证书，测试环境请设置false，生产环境建议优先尝试true，不行再false
            if (!SdkConfig.getConfig().isIfValidateRemoteCert()) {
                //解决由于服务器证书问题导致HTTPS无法访问的情况
                connection.setHostnameVerifier(new BaseHttpSslSocketFactory.TrustAnyHostnameVerifier());
                connection.setSSLSocketFactory(new BaseHttpSslSocketFactory());
            }
            return connection;
        }
        return httpUrlConnection;
    }

    /**
     * 将Map存储的对象，转换为key=value&key=value的字符
     */
    private String getRequestParamString(Map<String, String> requestParam, String coder) {
        if (null == coder || "".equals(coder)) {
            coder = "UTF-8";
        }
        StringBuilder sf = new StringBuilder(64);
        String reqstr = "";
        if (null != requestParam && 0 != requestParam.size()) {
            for (Entry<String, String> en : requestParam.entrySet()) {
                try {
                    sf.append(en.getKey())
                            .append("=")
                            .append(null == en.getValue() || "".equals(en.getValue()) ? "" : URLEncoder.encode(en.getValue(), coder))
                            .append("&");
                } catch (UnsupportedEncodingException e) {
                    LogUtil.writeErrorLog(e.getMessage(), e);
                    return "";
                }
            }
            reqstr = sf.substring(0, sf.length() - 1);
        }
        LogUtil.writeLog("Request Message:[" + reqstr + "]");
        return reqstr;
    }
}
