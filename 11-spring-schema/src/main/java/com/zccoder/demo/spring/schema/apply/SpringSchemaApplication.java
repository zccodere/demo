package com.zccoder.demo.spring.schema.apply;

import com.zccoder.demo.spring.schema.apply.service.impl.HelloServiceImpl;
import com.zccoder.demo.spring.schema.support.ServiceConfig;

import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ImportResource;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 启动类
 *
 * @author zc 2018-10-16
 **/
@ImportResource("classpath:apply-service.xml")
@SpringBootApplication
public class SpringSchemaApplication implements ApplicationContextAware, CommandLineRunner {

    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(SpringSchemaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Map<String, ServiceConfig> services = applicationContext.getBeansOfType(ServiceConfig.class);
        for (Map.Entry<String, ServiceConfig> serviceConfigEntry : services.entrySet()) {
            System.err.println("服务Bean ID：" + serviceConfigEntry.getKey());
            System.err.println("服务信息：" + serviceConfigEntry.getValue());
        }

        this.registerMapping();

    }

    /**
     * 手动注册Mapping
     */
    private void registerMapping() throws Exception {
        // 获取注册服务
        RequestMappingHandlerMapping handlerMapping = this.applicationContext.getBean(RequestMappingHandlerMapping.class);

        // 构建URL路径
        PatternsRequestCondition patternsRequestCondition = new PatternsRequestCondition("/helloService/hello");
        // 构建请求方式
        RequestMethodsRequestCondition requestMethodsRequestCondition = new RequestMethodsRequestCondition(RequestMethod.GET);
        // 构建映射信息
        RequestMappingInfo mappingInfo = new RequestMappingInfo(patternsRequestCondition, requestMethodsRequestCondition, null, null, null, null, null);
        // 构建处理方法
        Method hello = HelloServiceImpl.class.getMethod("hello", String.class);
        // 注册映射：映射信息、实例BeanID、处理方法
        handlerMapping.registerMapping(mappingInfo, "helloService", hello);
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
