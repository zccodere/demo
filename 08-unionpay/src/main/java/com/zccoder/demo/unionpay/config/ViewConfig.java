package com.zccoder.demo.unionpay.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 标题：视图配置<br>
 * 描述：视图配置<br>
 * 时间：2018/09/26<br>
 *
 * @author zc
 **/
@Configuration
public class ViewConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/html/pay").setViewName("order/pay");
        registry.addViewController("/html/undo").setViewName("order/undo");
    }
}
