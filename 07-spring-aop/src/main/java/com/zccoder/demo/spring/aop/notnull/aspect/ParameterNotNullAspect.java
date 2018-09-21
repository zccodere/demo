package com.zccoder.demo.spring.aop.notnull.aspect;

import java.lang.reflect.Method;

import com.zccoder.demo.spring.aop.notnull.annotation.ParameterNotNull;
import com.zccoder.demo.spring.aop.notnull.util.ReflexUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 参数非空切面处理类
 *
 * @author ZhangCheng
 * @version V1.0
 * @date 2017-03-20
 */
@Aspect
@Order(value = 2)
@Component
public class ParameterNotNullAspect {

    /**
     * 通过@Pointcut注解声明切点。
     * 拦截方法上面添加了@ParameterNotNull注解的方法
     */
    @Pointcut("@annotation(com.zccoder.demo.spring.aop.notnull.annotation.ParameterNotNull)")
    public void parameterNotNullPointCut() {
    }

    ;

    /**
     * 定义环绕通知
     *
     * @param point 切点
     * @return 目标方法执行之后的结果
     */
    @Around("parameterNotNullPointCut()")
    public Object doAround(ProceedingJoinPoint point) throws Exception {
        System.out.println("校验参数");

        // 获取即将执行的方法名称
        String targetMethodName = point.getSignature().getName();

        // 获取即将执行的方法全类名
        String targetClassName = point.getTarget().getClass().getName();
        Class<?> targetClass = Class.forName(targetClassName);

        // 获取即将执行的方法参数类型
        MethodSignature signature = (MethodSignature) point.getSignature();
        Class<?>[] targetParamType = signature.getParameterTypes();

        // 获取即将执行的方法参数名称
        String[] targetMethodAllParamNames = ReflexUtils.getFieldsName(targetClassName, targetMethodName);

        // 获取即将执行的方法参数值
        Object[] targetParamValues = point.getArgs();

        // 获取即将执行的方法对象
        Method targetMethod = targetClass.getMethod(targetMethodName, targetParamType);

        // 获取方法对象上的注解属性值
        ParameterNotNull parameterNotNull = targetMethod.getAnnotation(ParameterNotNull.class);
        String[] targetMethodSetParamNames = parameterNotNull.value();

        // 需要校验的参数名称
        String[] needValidNames = targetMethodAllParamNames;

        // 当注解属性值不为默认值时，将配置的参数名称赋给需要校验的参数名称数组
        if (!(targetMethodSetParamNames.length == 1 && "".equalsIgnoreCase(targetMethodSetParamNames[0]))) {
            needValidNames = targetMethodSetParamNames;
        }

        RespMessage respMessage = new RespMessage();
        // 对请求参数进行校验
        for (int i = 0; i < targetMethodAllParamNames.length; i++) {
            for (int j = 0; j < needValidNames.length; j++) {
                if (targetMethodAllParamNames[i].equalsIgnoreCase(needValidNames[j])) {
                    if (targetParamValues[i] == null || "" == targetParamValues[i]) {
                        respMessage.setCode("500");
                        respMessage.setMsg("请求参数[" + needValidNames[j] + "]不能为空");
                        return respMessage;
                    }
                }
            }
        }
        // 执行目标方法
        Object result = null;
        try {
            // 前置通知
            result = point.proceed();
            // 后置通知
        } catch (Throwable e) {
            // 异常通知
        }
        // 返回通知
        return result;
    }
}
