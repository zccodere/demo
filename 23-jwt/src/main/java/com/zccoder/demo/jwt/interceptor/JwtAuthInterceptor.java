package com.zccoder.demo.jwt.interceptor;

import com.zccoder.demo.jwt.configuration.JwtProperties;
import com.zccoder.demo.jwt.constant.JwtConst;
import com.zccoder.demo.jwt.domain.ResponseBody;
import com.zccoder.demo.jwt.domain.User;
import com.zccoder.demo.jwt.util.JsonUtils;
import com.zccoder.demo.jwt.util.JwtParserUtils;
import com.zccoder.demo.jwt.util.ThreadHolderUtils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;

/**
 * JWT用户登录认证拦截器
 *
 * @author zc
 * @date 2020/09/29
 */
public class JwtAuthInterceptor extends HandlerInterceptorAdapter {

    /**
     * 不拦截登录认证的接口路径
     */
    private static final List<String> EXCLUDE_PATH_PATTERNS = Arrays.asList("/register", "/login", "/refresh/token", "/logout", "/test1");

    public static List<String> excludePathPatterns() {
        return EXCLUDE_PATH_PATTERNS;
    }

    private JwtParser jwtParser;

    public JwtAuthInterceptor(JwtProperties jwtProperties) {
        jwtParser = JwtParserUtils.getJwtParser(jwtProperties.getSecret());
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(JwtConst.AUTHORIZATION);
        if (StringUtils.isNotBlank(token) && token.startsWith(JwtConst.BEARER)) {
            token = token.substring(JwtConst.BEARER.length());
        }
        if (StringUtils.isBlank(token)) {
            // 未传入token
            String info = "Unauthorized";
            responseWrite(response, ResponseBody.fail(String.valueOf(HttpStatus.UNAUTHORIZED), info));
            return false;
        }

        Jws<Claims> claimsJws;
        try {
            claimsJws = jwtParser.parseClaimsJws(token);
        } catch (ExpiredJwtException ex) {
            // 授权已过期
            String message = "身份过期";
            responseWrite(response, ResponseBody.fail(String.valueOf(HttpStatus.UNAUTHORIZED), message));
            return false;
        } catch (Exception ex) {
            // token错误
            String message = "Unauthorized";
            responseWrite(response, ResponseBody.fail(String.valueOf(HttpStatus.UNAUTHORIZED), message));
            return false;
        }

        // 解析用户信息
        User user = this.parserToken(claimsJws);
        if (Objects.isNull(user)) {
            // 用户数据非法
            String message = "用户数据非法";
            responseWrite(response, ResponseBody.fail(String.valueOf(HttpStatus.UNAUTHORIZED), message));
            return false;
        }

        ThreadHolderUtils.setUser(user);
        // 验证通过
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ThreadHolderUtils.clearUser();
    }

    private User parserToken(Jws<Claims> claimsJws) {
        try {
            String user = claimsJws.getBody().get("user", String.class);
            return JsonUtils.toJavaObject(user, User.class);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * response输出信息
     *
     * @param response     输出流
     * @param responseBody 响应体
     */
    private void responseWrite(HttpServletResponse response, ResponseBody<?, ?> responseBody) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(JsonUtils.toJsonString(responseBody));
    }

}
