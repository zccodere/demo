package com.zccoder.demo.spring.aop.notnull.util;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

/**
 * 反射工具类
 * @author ZhangCheng
 * @date 2017-03-20
 * @version V1.0
 */
public class ReflexUtils {
    
    /**
     * 获取方法参数列表的名称
     * @param clazzName 全类名称
     * @param methodName 方法名称
     * @return 方法参数列表名称
     * @throws Exception
     */
    public static String[] getFieldsName(String clazzName, String methodName) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        ClassClassPath classPath = new ClassClassPath(pool.getClass());
        pool.insertClassPath(classPath);
        CtClass cc = pool.get(clazzName);
        CtMethod cm = cc.getDeclaredMethod(methodName);
        MethodInfo methodInfo = cm.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        if (attr == null) {
            // exception
        }
        // 封装参数列表名称
        String[] paramNames = new String[cm.getParameterTypes().length];
        int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
        for (int i = 0; i < paramNames.length; i++){
            paramNames[i] = attr.variableName(i + pos);
        }
        return paramNames;
    }

}
