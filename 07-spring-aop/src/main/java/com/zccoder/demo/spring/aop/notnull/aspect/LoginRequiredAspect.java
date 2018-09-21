package com.zccoder.demo.spring.aop.notnull.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 登录校验切面处理类
 *
 * @author ZhangCheng
 * @version V1.0
 * @date 2017-03-20
 */
@Aspect
@Order(value = 1)
@Component
public class LoginRequiredAspect {

    @Pointcut("@annotation(com.zccoder.demo.spring.aop.notnull.annotation.LoginRequired)")
    public void loginRequiredAspectPointCut() {
    }

    ;

    /**
     * 定义环绕通知
     *
     * @param point 切点
     * @return 目标方法执行之后的结果
     */
    @Around("loginRequiredAspectPointCut()")
    public Object doAround(ProceedingJoinPoint point) throws Exception {

        System.out.println("校验登录，未实现");

        String targetMethodName = point.getSignature().getName();

        // 执行目标方法
        Object result = null;

        try {
            // 前置通知
            System.out.println("前置通知：执行方法：" + targetMethodName + ",参数：" + Arrays.asList(point.getArgs()));

            // 执行方法
            result = point.proceed();

            // 后置通知
            System.out.println("后置通知：执行方法：" + targetMethodName + result);
        } catch (Throwable e) {
            // 异常通知
            System.out.println("异常通知：执行方法：" + targetMethodName + e);
            throw new RuntimeException(e);
        }

        // 返回通知
        System.out.println("返回通知：执行方法：" + targetMethodName);
        return result;
    }
}
