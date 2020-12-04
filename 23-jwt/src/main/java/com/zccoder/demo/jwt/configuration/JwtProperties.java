package com.zccoder.demo.jwt.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * JWT配置参数类
 *
 * @author zc
 * @date 2020/09/29
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    /**
     * JWT签发者
     */
    private String issuer;
    /**
     * JWT密钥
     */
    private String secret;

}
