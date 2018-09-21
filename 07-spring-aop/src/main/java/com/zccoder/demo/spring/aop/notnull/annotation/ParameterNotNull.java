package com.zccoder.demo.spring.aop.notnull.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 参数非空切面拦截标记注解
 *
 * @author ZhangCheng
 * @version V1.0
 * @date 2017-03-20
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParameterNotNull {
    String[] value() default "";
}
