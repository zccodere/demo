package com.zccoder.demo.jwt.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zccoder.demo.jwt.AbstractTestSupport;
import com.zccoder.demo.jwt.constant.JwtConst;
import com.zccoder.demo.jwt.domain.EmptyMeta;
import com.zccoder.demo.jwt.domain.ResponseBody;
import com.zccoder.demo.jwt.dto.LoginDto;
import com.zccoder.demo.jwt.dto.LoginSuccessDto;
import com.zccoder.demo.jwt.util.JsonUtils;

import org.junit.Test;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 登录控制层测试类
 *
 * @author zc
 * @date 2020/09/29
 */
public class LoginControllerTest extends AbstractTestSupport {

    /**
     * 用户名密码注册
     */
    @Test
    public void register() throws Exception {
        LoginDto dto = new LoginDto();
        dto.setName("模拟用户");
        dto.setPassword("123456");
        String json = JsonUtils.toJsonString(dto);

        String response = mockMvc.perform(post("/register")
                .content(json)
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        TypeReference<ResponseBody<Void, EmptyMeta>> typeReference = new TypeReference<ResponseBody<Void, EmptyMeta>>() {
        };
        ResponseBody<Void, EmptyMeta> responseBody = objectMapper.readValue(response, typeReference);
        System.out.println(responseBody);
    }

    /**
     * 用户名密码登录
     */
    @Test
    public void login() throws Exception {
        LoginDto dto = new LoginDto();
        dto.setName("aaa");
        dto.setPassword("aaa");
        String json = JsonUtils.toJsonString(dto);

        String response = mockMvc.perform(post("/login")
                .content(json)
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        TypeReference<ResponseBody<LoginSuccessDto, EmptyMeta>> typeReference = new TypeReference<ResponseBody<LoginSuccessDto, EmptyMeta>>() {
        };
        ResponseBody<LoginSuccessDto, EmptyMeta> responseBody = objectMapper.readValue(response, typeReference);
        System.out.println(responseBody);
    }

    /**
     * 刷新授权令牌
     */
    @Test
    public void refreshToken() throws Exception {
        String refreshToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJlb3AtY29uc29sZSIsImlhdCI6MTYwMTI3ODc0OSwiZXhwIjoxNjAxMjg5NTQ5LCJ1c2VyIjoie1widXNlcl9pZFwiOjg4OCxcInVzZXJfbmFtZVwiOlwi5qih5ouf55So5oi3XCIsXCJhY19pZFwiOjg4OCxcInNob3BfaWRcIjo2MCxcInNob3BfbmFtZVwiOlwi6ICD5ouJ5bqX6ZO6XCJ9In0.7sK9SJH6bagcQj4Cvk-30-Xj1CCf3E4eeX97E8FT_S4";
        String response = mockMvc.perform(post("/refresh/token")
                .header(JwtConst.AUTHORIZATION, refreshToken)
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        TypeReference<ResponseBody<LoginSuccessDto, EmptyMeta>> typeReference = new TypeReference<ResponseBody<LoginSuccessDto, EmptyMeta>>() {
        };
        ResponseBody<LoginSuccessDto, EmptyMeta> responseBody = objectMapper.readValue(response, typeReference);
        System.out.println(responseBody);
    }

    /**
     * 退出登录
     */
    @Test
    public void logout() throws Exception {
        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJlb3AtY29uc29sZSIsImlhdCI6MTYwMTI3ODc0OSwiZXhwIjoxNjAxMjgyMzQ5LCJ1c2VyIjoie1widXNlcl9pZFwiOjg4OCxcInVzZXJfbmFtZVwiOlwi5qih5ouf55So5oi3XCIsXCJhY19pZFwiOjg4OCxcInNob3BfaWRcIjo2MCxcInNob3BfbmFtZVwiOlwi6ICD5ouJ5bqX6ZO6XCJ9In0.zB-Ho9duhCFpyQjZdXd7_VGHeU_mYg1mA_7bvxERMPU";
        String response = mockMvc.perform(post("/logout")
                .header(JwtConst.AUTHORIZATION, token)
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        TypeReference<ResponseBody<Void, EmptyMeta>> typeReference = new TypeReference<ResponseBody<Void, EmptyMeta>>() {
        };
        ResponseBody<Void, EmptyMeta> responseBody = objectMapper.readValue(response, typeReference);
        System.out.println(responseBody);
    }

    /**
     * 测试授权是否生效
     */
    @Test
    public void authTest() throws Exception {
        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJlb3AtY29uc29sZSIsImlhdCI6MTYwMTI3ODc0OSwiZXhwIjoxNjAxMjgyMzQ5LCJ1c2VyIjoie1widXNlcl9pZFwiOjg4OCxcInVzZXJfbmFtZVwiOlwi5qih5ouf55So5oi3XCIsXCJhY19pZFwiOjg4OCxcInNob3BfaWRcIjo2MCxcInNob3BfbmFtZVwiOlwi6ICD5ouJ5bqX6ZO6XCJ9In0.zB-Ho9duhCFpyQjZdXd7_VGHeU_mYg1mA_7bvxERMPU";
        String response = mockMvc.perform(get("/test")
                .header(JwtConst.AUTHORIZATION, token)
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        TypeReference<ResponseBody<Void, EmptyMeta>> typeReference = new TypeReference<ResponseBody<Void, EmptyMeta>>() {
        };
        ResponseBody<Void, EmptyMeta> responseBody = objectMapper.readValue(response, typeReference);
        System.out.println(responseBody);
    }

}
