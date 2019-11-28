package com.zccoder.demo.time.controller;

import com.zccoder.demo.time.domain.RequestBean;
import com.zccoder.demo.time.domain.ResponseBean;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 时间控制层
 *
 * @author zc 2019-08-30
 */
@Validated
@RestController
@RequestMapping("/time")
public class TimeController {

    /**
     * 增加一天
     *
     * @param requestBean 请求对象
     * @return 响应对象
     */
    @GetMapping
    public ResponseBean getPlusOneDay(@Valid RequestBean requestBean) {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setResultDate(requestBean.getStartDate().plusDays(1));
        responseBean.setResultTime(requestBean.getStartTime().plusDays(1));
        return responseBean;
    }

    /**
     * 增加一天
     *
     * @param requestBean 请求对象
     * @return 响应对象
     */
    @PostMapping
    public ResponseBean postPlusOneDay(@Valid @RequestBody RequestBean requestBean) {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setResultDate(requestBean.getStartDate().plusDays(1));
        responseBean.setResultTime(requestBean.getStartTime().plusDays(1));
        return responseBean;
    }
}
