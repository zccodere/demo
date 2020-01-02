package com.demo.jfinal.util;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Http客户端（使用OkHttp）
 *
 * @author zc 2020-01-02
 */
public class HttpUtils {

    private static final MediaType APPLICATION_JSON_UTF8 = MediaType.get("application/json; charset=utf-8");
    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final SerializeConfig config = new SerializeConfig();

    private static final OkHttpClient httpClient;

    static {
        config.setPropertyNamingStrategy(PropertyNamingStrategy.SnakeCase);
        httpClient = new OkHttpClient()
                .newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .callTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    public static String doGet(String auth, String url, Map<String, String> param) {
        if (Objects.nonNull(param)) {
            StringBuilder targetUrlStr = new StringBuilder(url).append("?");
            for (Map.Entry<String, String> entry : param.entrySet()) {
                targetUrlStr.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            url = targetUrlStr.substring(0, targetUrlStr.length() - 1);
        }
        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(url);
        if (StringUtils.isNotBlank(auth)) {
            requestBuilder.header(HEADER_AUTHORIZATION, auth);
        }
        Request request = requestBuilder.build();
        try (Response response = httpClient.newCall(request).execute()) {
            if (Objects.isNull(response.body())) {
                return StringUtils.EMPTY;
            }
            return response.body().string();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String doPost(String auth, String url, Object object) {
        RequestBody body;
        if (Objects.isNull(object)) {
            body = RequestBody.create(APPLICATION_JSON_UTF8, "string");
        } else {
            String json = JSONObject.toJSONString(object, config);
            System.out.println("http post 请求参数：" + json);
            body = RequestBody.create(APPLICATION_JSON_UTF8, json);
        }

        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(url);
        requestBuilder.post(body);
        if (StringUtils.isNotBlank(auth)) {
            requestBuilder.header(HEADER_AUTHORIZATION, auth);
        }
        Request request = requestBuilder.build();
        try (Response response = httpClient.newCall(request).execute()) {
            if (Objects.isNull(response.body())) {
                return StringUtils.EMPTY;
            }
            return response.body().string();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String doPatch(String auth, String url, Object object) {
        RequestBody body;
        if (Objects.isNull(object)) {
            body = RequestBody.create(APPLICATION_JSON_UTF8, "string");
        } else {
            String json = JSONObject.toJSONString(object, config);
            System.out.println("http patch 请求参数：" + json);
            body = RequestBody.create(APPLICATION_JSON_UTF8, json);
        }

        Request.Builder requestBuilder = new Request.Builder();
        requestBuilder.url(url);
        requestBuilder.patch(body);
        if (StringUtils.isNotBlank(auth)) {
            requestBuilder.header(HEADER_AUTHORIZATION, auth);
        }
        Request request = requestBuilder.build();
        try (Response response = httpClient.newCall(request).execute()) {
            if (Objects.isNull(response.body())) {
                return StringUtils.EMPTY;
            }
            return response.body().string();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
