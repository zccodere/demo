package com.zccoder.demo.unionpay.sdk;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * 忽略验证ssl证书
 *
 * @author zc 2018-09-27
 **/
public class BaseHttpSslSocketFactory extends SSLSocketFactory {

    private SSLContext getSslContext() {
        return createEasySslContext();
    }

    @Override
    public Socket createSocket(InetAddress arg0, int arg1, InetAddress arg2,
                               int arg3) throws IOException {
        return getSslContext().getSocketFactory().createSocket(arg0, arg1,
                arg2, arg3);
    }

    @Override
    public Socket createSocket(String arg0, int arg1, InetAddress arg2, int arg3)
            throws IOException {
        return getSslContext().getSocketFactory().createSocket(arg0, arg1,
                arg2, arg3);
    }

    @Override
    public Socket createSocket(InetAddress arg0, int arg1) throws IOException {
        return getSslContext().getSocketFactory().createSocket(arg0, arg1);
    }

    @Override
    public Socket createSocket(String arg0, int arg1) throws IOException {
        return getSslContext().getSocketFactory().createSocket(arg0, arg1);
    }

    @Override
    public String[] getSupportedCipherSuites() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String[] getDefaultCipherSuites() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Socket createSocket(Socket arg0, String arg1, int arg2, boolean arg3)
            throws IOException {
        return getSslContext().getSocketFactory().createSocket(arg0, arg1,
                arg2, arg3);
    }

    private SSLContext createEasySslContext() {
        try {
            SSLContext context = SSLContext.getInstance("SSL");
            context.init(null,
                    new TrustManager[]{MyX509TrustManager.manger}, null);
            return context;
        } catch (Exception e) {
            LogUtil.writeErrorLog(e.getMessage(), e);
            return null;
        }
    }

    public static class MyX509TrustManager implements X509TrustManager {

        static MyX509TrustManager manger = new MyX509TrustManager();

        MyX509TrustManager() {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }
    }

    /**
     * 解决由于服务器证书问题导致HTTPS无法访问的情况 PS:HTTPS hostname wrong: should be <localhost>
     */
    public static class TrustAnyHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            //直接返回true
            return true;
        }
    }
}
