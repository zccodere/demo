package com.zccoder.demo.jwt.service.impl;

import com.zccoder.demo.jwt.configuration.JwtProperties;
import com.zccoder.demo.jwt.constant.DmoJwtErrorCode;
import com.zccoder.demo.jwt.constant.JwtConst;
import com.zccoder.demo.jwt.domain.User;
import com.zccoder.demo.jwt.domain.UserAll;
import com.zccoder.demo.jwt.dto.LoginDto;
import com.zccoder.demo.jwt.dto.LoginSuccessDto;
import com.zccoder.demo.jwt.exception.DemoJwtException;
import com.zccoder.demo.jwt.service.LoginService;
import com.zccoder.demo.jwt.util.JsonUtils;
import com.zccoder.demo.jwt.util.JwtParserUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;

/**
 * 登录服务层实现类
 *
 * @author zc
 * @date 2020/09/29
 */
@Service
public class LoginServiceImpl implements LoginService {

    private static final Map<String, UserAll> USER_MAP = new HashMap<>(16);
    private static final Map<String, String> USER_TOKEN_MAP = new HashMap<>(16);
    private static final Map<String, String> USER_REFRESH_TOKEN_MAP = new HashMap<>(16);
    private static final AtomicInteger USER_ID_GENERATOR = new AtomicInteger(10000);
    private static final ZoneOffset ZONE_OFFSET = OffsetDateTime.now().getOffset();

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public void register(LoginDto dto) {
        if (USER_MAP.containsKey(dto.getName())) {
            throw new DemoJwtException(DmoJwtErrorCode.USER_REGISTER_NAME_REPEAT);
        }

        UserAll user = UserAll.of(dto);
        user.setUserId(USER_ID_GENERATOR.decrementAndGet());
        USER_MAP.put(user.getUserName(), user);
    }

    @Override
    public LoginSuccessDto login(LoginDto dto) {
        UserAll userAll = USER_MAP.get(dto.getName());

        if (Objects.isNull(userAll)) {
            throw new DemoJwtException(DmoJwtErrorCode.USER_LOGIN_NAME_NOT_REGISTER);
        }

        if (!userAll.getPassword().equals(dto.getPassword())) {
            throw new DemoJwtException(DmoJwtErrorCode.USER_LOGIN_NAME_NOT_REGISTER);
        }

        // 生成token
        return this.getToken(userAll, true);
    }

    @Override
    public LoginSuccessDto refreshToken(String refreshToken) {
        // 解析token获取用户信息
        User user = this.parserToken(refreshToken);

        // 判断refreshToken是否与redis中的相同
        String refreshTokenCache = USER_REFRESH_TOKEN_MAP.get(user.getUserName());
        if (!refreshToken.equals(refreshTokenCache)) {
            // 传入的refreshToken与redis中的refreshToken不同，则认为传入的refreshToken非法
            throw new DemoJwtException(DmoJwtErrorCode.USER_REFRESH_TOKEN_INVALID);
        }

        UserAll userAll = USER_MAP.get(user.getUserName());
        if (Objects.isNull(userAll)) {
            throw new DemoJwtException(DmoJwtErrorCode.USER_LOGIN_NAME_NOT_REGISTER);
        }

        // 重新生成新的token和refreshToken
        return this.getToken(userAll, false);
    }

    @Override
    public void logout(String token) {
        // 解析token获取用户信息
        User user = null;
        try {
            user = this.parserToken(token);
        } catch (Exception ex) {
            // 忽略
        }
        if (Objects.isNull(user)) {
            return;
        }

        // 从Map中移除token和refreshToken
        USER_TOKEN_MAP.remove(user.getUserName());
        USER_REFRESH_TOKEN_MAP.remove(user.getUserName());
    }

    private LoginSuccessDto getToken(UserAll userAll, boolean isLogin) {
        if (isLogin) {
            // 重复登录场景校验
            String tokenRedis = USER_TOKEN_MAP.get(userAll.getUserName());
            if (StringUtils.isNotBlank(tokenRedis)) {
                throw new DemoJwtException(DmoJwtErrorCode.USER_LOGIN_REPEAT);
            }
        }

        // 生成token
        User user = userAll.toUser();
        String token = JwtConst.BEARER + this.sign(user, 60L);
        // 生成refreshToken
        String refreshToken = JwtConst.BEARER + this.sign(user, 180L);

        // 这里将token存入内存缓存
        USER_TOKEN_MAP.put(userAll.getUserName(), token);
        USER_REFRESH_TOKEN_MAP.put(userAll.getUserName(), refreshToken);

        // 返回token
        LoginSuccessDto loginSuccessDto = userAll.toLoginSuccessDto();
        loginSuccessDto.setToken(token);
        loginSuccessDto.setRefreshToken(refreshToken);
        return loginSuccessDto;
    }

    /**
     * 获取授权令牌
     *
     * @param user    用户基础信息
     * @param timeout 过期时间（单位：分钟）
     * @return 授权令牌
     */
    private String sign(User user, long timeout) {
        LocalDateTime now = LocalDateTime.now();

        Claims claims = new DefaultClaims();
        claims.put("user", JsonUtils.toJsonString(user));

        return Jwts.builder()
                .setIssuer(this.jwtProperties.getIssuer())
                .setIssuedAt(new Date(now.toInstant(ZONE_OFFSET).toEpochMilli()))
                .setExpiration(new Date(now.plusMinutes(timeout).toInstant(ZONE_OFFSET).toEpochMilli()))
                .addClaims(claims)
                .signWith(Keys.hmacShaKeyFor(this.jwtProperties.getSecret().getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    private User parserToken(String token) {
        try {
            if (StringUtils.isNotBlank(token) && token.startsWith(JwtConst.BEARER)) {
                token = token.substring(JwtConst.BEARER.length());
            }
            Jws<Claims> claimsJws = JwtParserUtils.getJwtParser(this.jwtProperties.getSecret()).parseClaimsJws(token);
            String user = claimsJws.getBody().get("user", String.class);
            return JsonUtils.toJavaObject(user, User.class);
        } catch (ExpiredJwtException ex) {
            // 授权已过期
            throw new DemoJwtException(DmoJwtErrorCode.USER_REFRESH_TOKEN_TIMEOUT);
        } catch (SecurityException ex) {
            // 验签失败
            throw new DemoJwtException(DmoJwtErrorCode.USER_REFRESH_TOKEN_INVALID);
        }
    }

}
