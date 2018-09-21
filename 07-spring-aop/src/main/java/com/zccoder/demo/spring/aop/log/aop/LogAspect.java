package com.zccoder.demo.spring.aop.log.aop;

import java.lang.reflect.Method;
import java.util.Arrays;

import com.zccoder.demo.spring.aop.log.dto.ReturnMessage;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * 日志切面
 * // 通过@Aspect注解声明一个切面。
 * // 通过@Component让此切面成为Spring容器管理的Bean。
 *
 * @author ZhangCheng
 * @version V1.0
 * @date 2017-03-20
 */
@Aspect
@Component
public class LogAspect {

    /**
     * // 通过@Pointcut注解声明切点。
     */
    @Pointcut("@annotation(com.zccoder.demo.spring.aop.log.aop.Action)")
    public void annotationPointCut() {
    }

    /**
     * 环绕通知
     * //    @Around("annotationPointCut()")
     *
     * @param pjd ProceedingJoinPoint
     * @return 结果
     * @throws Exception 异常
     */
    public Object around(ProceedingJoinPoint pjd) throws Exception {

        Object result = null;
        String methodName = pjd.getSignature().getName();

        // 获取方法返回值类型
        Class returnType = ((MethodSignature) pjd.getSignature()).getReturnType();

        // 访问目标方法的参数
        Object[] args = pjd.getArgs();

        //获取即将执行方法的名称
        System.out.println("请求执行方法：" + pjd.getSignature().getName());
        System.out.println("请求执行方法2：" + pjd.getTarget().getClass());

        String declaringTypeName =
                pjd.getSignature().getName();

        for (int i = 0; i < args.length; i++) {

            System.out.println("获取请求参数：" + args[i]);
            if (args[i] == null || args[i] == "") {
                ReturnMessage returnMessage = new ReturnMessage();
                returnMessage.setCode("500");
                returnMessage.setMsg("请求参数不能为空");
                return returnMessage;
            }
        }


//        if (args != null && args.length > 0 && args[0].getClass() == String.class) {
//            args[0] = "改变后的参数1";
//        }


        // 执行目标方法
        try {
            // 前置通知
            System.out.println("前置通知：执行方法：" + methodName + ",参数：" + Arrays.asList(pjd.getArgs()));

            result = pjd.proceed();

            // 后置通知
            System.out.println("后置通知：执行方法：" + methodName + result);

        } catch (Throwable e) {

            // 异常通知
            System.out.println("异常通知：执行方法：" + methodName + e);

        }

        // 返回通知
        System.out.println("返回通知：执行方法：" + methodName);

        return result;
    }

    /**
     * // 通过@After注解声明一个建言，并使用@Pointcut定义的切点。
     *
     * @param joinPoint 切点
     */
    @After("annotationPointCut()")
    public void after(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Action action = method.getAnnotation(Action.class);
//         通过反射可获得声明注解上的属性，然后做日志记录相关的操作，下面的相同。
        System.out.println("注解式拦截：方法名：" + method.getName());
//        System.out.println("注解式拦截：方法名："+action.toString());
//        System.out.println("注解式拦截 " + action.name());
    }

    // 通过@Before注解声明一个建言，此建言直接使用拦截规则作为参数。
//    @Before("execution(* com.zccoder.study.spring.my.*(..))")
//    public void before(JoinPoint joinPoint) {
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Method method = signature.getMethod();
//        System.out.println("方法规则式拦截 " + method.getName());
//    }
}
