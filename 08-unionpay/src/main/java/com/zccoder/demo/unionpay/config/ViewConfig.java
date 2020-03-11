package com.zccoder.demo.unionpay.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 视图配置
 *
 * @author zc 2018-09-26
 **/
@Configuration
public class ViewConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/html/pay").setViewName("order/pay");
        registry.addViewController("/html/undo").setViewName("order/undo");
    }
}
