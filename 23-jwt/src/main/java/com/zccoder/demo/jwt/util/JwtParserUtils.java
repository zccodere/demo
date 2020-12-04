package com.zccoder.demo.jwt.util;

import java.util.concurrent.ConcurrentHashMap;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.experimental.UtilityClass;

/**
 * JWT解析工具类
 *
 * @author zc
 * @date 2020/09/29
 */
@UtilityClass
public class JwtParserUtils {

    private static final ConcurrentHashMap<String, JwtParser> JWT_PARSER_CACHE = new ConcurrentHashMap<>();

    /**
     * 基于JWT密钥获取JWT解析器
     *
     * @param secret JWT密钥
     * @return JWT解析器
     */
    public static JwtParser getJwtParser(String secret) {
        return JWT_PARSER_CACHE.computeIfAbsent(secret, s -> Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build());
    }

}
