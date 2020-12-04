package com.zccoder.demo.jwt.controller;

import com.zccoder.demo.jwt.constant.JwtConst;
import com.zccoder.demo.jwt.domain.EmptyMeta;
import com.zccoder.demo.jwt.domain.ResponseBody;
import com.zccoder.demo.jwt.domain.User;
import com.zccoder.demo.jwt.dto.LoginDto;
import com.zccoder.demo.jwt.dto.LoginSuccessDto;
import com.zccoder.demo.jwt.service.LoginService;
import com.zccoder.demo.jwt.util.JsonUtils;
import com.zccoder.demo.jwt.util.ThreadHolderUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 登录控制层
 *
 * @author zc
 * @date 2020/09/29
 */
@RestController
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    /**
     * 用户名密码注册
     *
     * @param dto 用户名密码
     * @return 成功
     */
    @PostMapping("/register")
    public ResponseBody<Void, EmptyMeta> register(@RequestBody @Valid LoginDto dto) {
        this.loginService.register(dto);
        return ResponseBody.success();
    }

    /**
     * 用户名密码登录
     *
     * @return 登录结果
     */
    @GetMapping("/login")
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    public ResponseBody<Void, EmptyMeta> login() {
        return ResponseBody.success();
    }

    /**
     * 用户名密码登录
     *
     * @param dto 用户名密码
     * @return 登录结果
     */
    @PostMapping("/login")
    public ResponseBody<LoginSuccessDto, EmptyMeta> login(@RequestBody @Valid LoginDto dto) {
        return ResponseBody.success(this.loginService.login(dto));
    }

    /**
     * 刷新授权令牌
     *
     * @param refreshToken 用于刷新授权令牌的令牌
     * @return 新的授权令牌
     */
    @PostMapping("/refresh/token")
    public ResponseBody<LoginSuccessDto, EmptyMeta> refreshToken(@RequestHeader(JwtConst.AUTHORIZATION) String refreshToken) {
        return ResponseBody.success(this.loginService.refreshToken(refreshToken));
    }

    /**
     * 退出登录
     *
     * @param token 授权令牌
     * @return 成功
     */
    @PostMapping("/logout")
    public ResponseBody<Void, EmptyMeta> logout(@RequestHeader(JwtConst.AUTHORIZATION) String token) {
        this.loginService.logout(token);
        return ResponseBody.success();
    }

    /**
     * 测试授权是否生效
     *
     * @return 成功
     */
    @GetMapping("/test")
    public ResponseBody<Void, EmptyMeta> authTest() {
        User user = ThreadHolderUtils.getUser();
        String message = JsonUtils.toJsonString(user);
        logger.info("用户登录认证成功，用户信息：{}", message);
        return ResponseBody.success();
    }

}
