package com.zccoder.demo.es.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ElasticSearch配置类
 *
 * @author zc 2019-06-25
 */
@Configuration
public class EsConfig {

    @Value("${elasticsearch.host}")
    private String host;
    @Value("${elasticsearch.port}")
    private int port;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        // 如果使用了账号密码
        boolean usePassword = false;
        if (usePassword) {
            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elastic", "123456"));
        }
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(host, port, "http"))
                        .setHttpClientConfigCallback(httpAsyncClientBuilder -> {
                            //这里可以设置一些参数，比如cookie存储、代理等等
                            httpAsyncClientBuilder.disableAuthCaching();
                            return httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                        })
        );
        return client;
    }
}
