package com.zccoder.demo.spring.aop.mark;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 切面，用于测试切面拦截生效场景
 *
 * @author zc 2020-04-24
 */
@Aspect
@Component
public class MarkAspect {


    @Pointcut("@annotation(com.zccoder.demo.spring.aop.mark.Mark)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        // 获取方法签名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        // 被拦截的方法
        Method targetMethod = methodSignature.getMethod();

        System.out.println("Before：" + targetMethod.getDeclaringClass().getName() + "#" + targetMethod.getName());
    }

}
