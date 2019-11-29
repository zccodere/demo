package com.zccoder.demo.time.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zccoder.demo.time.AppTestSupport;
import com.zccoder.demo.time.domain.RequestBean;
import com.zccoder.demo.time.domain.ResponseBean;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 时间控制层测试类
 *
 * @author zc 2019-08-30
 */
public class TimeControllerTest extends AppTestSupport {

    /**
     * 增加一天
     */
    @Test
    public void getPlusOneDay() throws Exception {
        // 构建请求参数
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>(4);
        params.add("start_date", "2019-08-30");
        params.add("start_time", "2019-08-30 22:36:20");
        // 执行调用请求
        String response = mockMvc.perform(get("/time")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .params(params))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        TypeReference<ResponseBean> typeReference = new TypeReference<ResponseBean>() {
        };
        ResponseBean responseBean = objectMapper.readValue(response, typeReference);
        System.out.println(responseBean);
    }

    /**
     * 增加一天
     */
    @Test
    public void postPlusOneDay() throws Exception {
        // 构建请求参数
        RequestBean requestBean = new RequestBean();
        requestBean.setStartDate(LocalDate.now());
        requestBean.setStartTime(LocalDateTime.now());
        String requestJson = objectMapper.writeValueAsString(requestBean);
        // 执行调用请求
        String response = mockMvc.perform(post("/time")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
        TypeReference<ResponseBean> typeReference = new TypeReference<ResponseBean>() {
        };
        ResponseBean responseBean = objectMapper.readValue(response, typeReference);
        System.out.println(responseBean);
    }
}
